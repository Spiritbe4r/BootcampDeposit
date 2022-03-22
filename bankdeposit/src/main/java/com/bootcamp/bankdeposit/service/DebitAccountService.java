package com.bootcamp.bankdeposit.service;

import com.bootcamp.bankdeposit.models.dto.DebitAccountDTO;
import reactor.core.publisher.Mono;

public interface DebitAccountService {

  Mono<DebitAccountDTO> findByAccountNumber(String typeofdebit, String accountNumber);


  Mono<DebitAccountDTO> updateDebit(String typeofdebit, DebitAccountDTO account);
}
