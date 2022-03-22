package com.bootcamp.bankdeposit.service.impl;

import com.bootcamp.bankdeposit.models.dto.AccountDTO;
import com.bootcamp.bankdeposit.models.dto.DepositDTO;
import com.bootcamp.bankdeposit.models.entities.Deposit;
import com.bootcamp.bankdeposit.repository.DepositRepository;
import com.bootcamp.bankdeposit.service.DepositService;
import com.bootcamp.bankdeposit.util.AppUtils;
import com.bootcamp.bankdeposit.util.Constants;
import java.math.BigDecimal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Service
public class DepositServicesImpl implements DepositService {

  @Value("${microservice-accounts.uri}")
  private String urlAccounts;
  @Value("${apiclient.uri}")
  private String urlApigateway;


  private static final Logger LOGGER = LoggerFactory.getLogger(DepositServicesImpl.class);
  @Autowired
  private DepositRepository depositRepository;

  @Autowired
  private WebClient.Builder webClient;
  @Autowired
  private RestTemplate restTemplate;

  /**
   * get all deposits flux.
   *
   * @return the flux
   */
  public Flux<DepositDTO> getDeposits() {

    LOGGER.debug("In getDeposit()");
    return depositRepository.findAll().map(AppUtils::entityToDto);
  }

  /**
   * get one deposit by mono.
   *
   * @param id the request
   * @return the mono
   */
  @Override
  public Mono<DepositDTO> getDepositById(String id) {
    return depositRepository.findById(id).map(AppUtils::entityToDto);
  }

  /**
   * save a deposit mono.
   *
   * @param depositDto the json info
   * @return the mono
   */
  public Mono<DepositDTO> saveDeposit(DepositDTO depositDto) {


    try {
      AccountDTO account = restTemplate.getForObject(urlApigateway + urlAccounts
            + depositDto.getToAccountId(), AccountDTO.class);
      LOGGER.debug("restTemplate:" + account.getAccountNumber());

      if (approveDeposit(account, depositDto)) {
        LOGGER.debug("calculateBalance:");
        calculateBalance(account, depositDto);
        LOGGER.debug("updateBalanceAccount:");
        updateBalanceAccount(account);
        LOGGER.debug("savingDeposit:");
        return savingDeposit(depositDto);
      } else {
        throw new Exception("Error: Deposito no permitido");
      }
    } catch (Exception e) {
      LOGGER.error("TransactionError", e);
      //rolback transaction
      return null;
    }
  }

  private Mono<DepositDTO> savingDeposit(DepositDTO depositDto) {
    LOGGER.debug("Service.savingDeposit");
    return Mono.just(depositDto).map(AppUtils::dtoToEntity)
          .flatMap(depositRepository::insert)
          .map(AppUtils::entityToDto);
  }

  /**
   * get one deposit by mono.
   *
   * @param id the deposit id
   * @return the mono
   * @body depositDtomono
   */
  public Mono<DepositDTO> updateDeposit(Mono<DepositDTO> depositdtoMono, String id) {
    return depositRepository.findById(id)
          .flatMap(p -> depositdtoMono.map(AppUtils::dtoToEntity)
                .doOnNext(e -> e.setId(id)))
          .flatMap(depositRepository::save)
          .map(AppUtils::entityToDto);
  }

  /**
   * delete one deposit mono.
   *
   * @param id the deposit id
   * @return void
   */
  public Mono<Void> deleteDeposit(String id) {
    return depositRepository.deleteById(id);
  }

  @Override
  public Flux<Deposit> getAll() {
    return depositRepository.findAll();
  }

  @Override
  public Mono<Deposit> getById(String s) {
    return depositRepository.findById(s);
  }

  @Override
  public Mono<Deposit> save(Deposit obj) {
    return save(obj);
  }

  @Override
  public Mono<Deposit> update(Mono<Deposit> obj, String s) {
    return depositRepository.findById(s)
          .doOnNext(e -> e.setId(s))
          .flatMap(depositRepository::save);
  }

  @Override
  public Mono<Void> delete(String s) {
    return depositRepository.deleteById(s);
  }


  private void updateBalanceAccount(AccountDTO account) {
    account.setMovementPerMonth(account.getMovementPerMonth() + 1);
    restTemplate.put(urlApigateway + urlAccounts + account.getId(), account);
  }

  /**
   * Pasivos (cuentas bancarias)
   * -Ahorro:
   * libre  de  comisión  por  mantenimiento  y  con  un  límite máximo de movimientos mensuales.
   * -Cuenta  corriente:  posee  comisión  de mantenimiento y  sin  límite de movimientos mensuales.
   * -Plazo  fijo:  libre  de  comisión  por  mantenimiento, solo  permite  un movimiento de
   * retiro o depósito en un día específico del mes.
   */
  private boolean approveDeposit(AccountDTO account, DepositDTO depositDto) {
    boolean resp = false;
    if (Constants.TIPO_CUENTA_PLAZO.equalsIgnoreCase(account.getAccountType())) {
      if (Constants.CAN_BE_DEPOSIT.equalsIgnoreCase(account.getCanBeDeposit())) {

        resp = true;
      }
    } else if (Constants.TIPO_CUENTA_AHORRO.equalsIgnoreCase(account.getAccountType())) {
      if (account.getMovementPerMonth() <= account.getMaxLimitMovementPerMonth()) {
        resp = true;
      }
    } else if (Constants.TIPO_CUENTA_CORRIENTE.equalsIgnoreCase(account.getAccountType())) {
      resp = true;
    }
    return resp;
  }

  private void calculateBalance(AccountDTO account, DepositDTO depositDto)
        throws NumberFormatException {

    BigDecimal balance = BigDecimal.valueOf(account.getBalance());
    BigDecimal amount = BigDecimal.valueOf(depositDto.getAmount());
    BigDecimal newBalance = balance.add(amount);

    account.setBalance(newBalance.doubleValue());

  }


}
