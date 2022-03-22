package com.bootcamp.bankdeposit.repository;

import com.bootcamp.bankdeposit.models.entities.Deposit;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

@Configuration
public interface DepositRepository extends ReactiveMongoRepository<Deposit, String> {


  /*Mono<DepositDto> findByName(String name);*/
  //Mono<DepositDto> findByDepositNumber(String depositNumber);
}
