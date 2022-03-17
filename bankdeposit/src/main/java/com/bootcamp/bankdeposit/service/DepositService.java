package com.bootcamp.bankdeposit.service;

import com.bootcamp.bankdeposit.dto.DepositDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface DepositService {

    Flux<DepositDto> getDeposit();
    Mono<DepositDto> getDepositById(String id);

    //Mono<DepositDto> getDepositByName(String name);

    //Mono<DepositDto> getDepositByDepositNumber(String depositNumber);

    Mono<DepositDto> saveDeposit(DepositDto depositDtoMono);

    Mono<DepositDto> updateDeposit(Mono<DepositDto> depositDtoMono, String id);

    Mono<Void> deleteDeposit(String id);
}
