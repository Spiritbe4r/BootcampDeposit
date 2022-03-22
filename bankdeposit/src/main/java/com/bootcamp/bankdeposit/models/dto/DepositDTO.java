package com.bootcamp.bankdeposit.models.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DepositDTO {
  private String id;
  private Double amount;
  private String currency;
  private String idClient;
  private String fromAccountNumber;
  private String toAccountNumber;
  private String toAccountId;
  private String depositor;
  private String timestamp;

  public DepositDTO(Double amount, String currency, String idClient, String fromAccountNumber,
				String toAccountNumber, String depositor, String timestamp) {
    this.amount = amount;
    this.currency = currency;
    this.idClient = idClient;
    this.fromAccountNumber = fromAccountNumber;
    this.toAccountNumber = toAccountNumber;
    this.depositor = depositor;
    this.timestamp = timestamp;
  }
}
