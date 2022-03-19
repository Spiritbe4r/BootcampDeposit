package com.bootcamp.bankdeposit.service;

import com.bootcamp.bankdeposit.bean.Deposit;
import com.bootcamp.bankdeposit.dto.DepositDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface DepositService extends CrudService<Deposit,String> {

    Flux<DepositDTO> getDeposit();
    Mono<DepositDTO> getDepositById(String id);

    //Mono<DepositDto> getDepositByName(String name);

    //Mono<DepositDto> getDepositByDepositNumber(String depositNumber);

    Mono<DepositDTO> saveDeposit(DepositDTO depositDtoMono);

    Mono<DepositDTO> updateDeposit(Mono<DepositDTO> depositDtoMono, String id);

    Mono<Void> deleteDeposit(String id);
}
