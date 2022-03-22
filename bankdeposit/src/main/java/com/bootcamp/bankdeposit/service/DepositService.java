package com.bootcamp.bankdeposit.service;

import com.bootcamp.bankdeposit.models.dto.DepositDTO;
import com.bootcamp.bankdeposit.models.entities.Deposit;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface DepositService extends CrudService<Deposit, String> {

  /**
   * get all deposits flux.
   *
   * @return the flux
   */
  Flux<DepositDTO> getDeposits();

  /**
   * get one deposit by mono.
   *
   * @param id the request
   * @return the mono
   */
  Mono<DepositDTO> getDepositById(String id);

  //Mono<DepositDto> getDepositByName(String name);

  //Mono<DepositDto> getDepositByDepositNumber(String depositNumber);

  /**
   * save a deposit mono.
   *
   * @param depositDto the json info
   * @return the mono
   */
  Mono<DepositDTO> saveDeposit(DepositDTO depositDto);

  /**
   * get one deposit by mono.
   *
   * @param id the deposit id
   * @return the mono
   * @body depositDtomono
   */
  Mono<DepositDTO> updateDeposit(Mono<DepositDTO> depositDtomono, String id);

  /**
   * delete one deposit mono.
   *
   * @param id the deposit id
   * @return void
   */
  Mono<Void> deleteDeposit(String id);
}
