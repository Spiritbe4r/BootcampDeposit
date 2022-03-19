package com.bootcamp.bankdeposit.repository;

import com.bootcamp.bankdeposit.bean.Deposit;
import com.bootcamp.bankdeposit.dto.DepositDTO;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

@Configuration
public interface DepositRepository extends ReactiveMongoRepository<Deposit, String> {


    /*Mono<DepositDto> findByName(String name);*/
    //Mono<DepositDto> findByDepositNumber(String depositNumber);
}
