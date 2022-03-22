package com.bootcamp.bankdeposit.service.impl;

import com.bootcamp.bankdeposit.models.dto.DebitAccountDTO;
import com.bootcamp.bankdeposit.service.DebitAccountService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class DebitAccountServiceImpl implements DebitAccountService {

  @Override
  public Mono<DebitAccountDTO> findByAccountNumber(String typeofdebit, String accountNumber) {
    return null;
  }

  @Override
  public Mono<DebitAccountDTO> updateDebit(String typeofdebit, DebitAccountDTO account) {
    return null;
  }
}
