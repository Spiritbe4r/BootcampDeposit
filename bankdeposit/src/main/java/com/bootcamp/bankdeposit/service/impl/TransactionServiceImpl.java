package com.bootcamp.bankdeposit.service.impl;

import com.bootcamp.bankdeposit.models.dto.TransactionDTO;
import com.bootcamp.bankdeposit.service.TransactionDTOService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class TransactionServiceImpl implements TransactionDTOService {

  WebClient webClient;

  /**
   * make a call to a transactions microservice.
   *
   * @param transactionDto the deposit id
   * @return a transaction
   */
  @Override
  public Mono<TransactionDTO> saveTransaction(TransactionDTO transactionDto) {
    return webClient.post()
          .body(Mono.just(transactionDto), TransactionDTO.class)
          .retrieve()
          .bodyToMono(TransactionDTO.class);
  }
}
