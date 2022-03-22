package com.bootcamp.bankdeposit.service;

import com.bootcamp.bankdeposit.models.dto.TransactionDTO;
import reactor.core.publisher.Mono;

public interface TransactionDTOService {

  Mono<TransactionDTO> saveTransaction(TransactionDTO transactionDto);
}
