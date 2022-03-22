package com.bootcamp.bankdeposit.models.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class DebitAccountDTO {
  private String id;
  private double amount;
  private String customerIdentityNumber;
  private String typeAccount;
  private String accountNumber;
  private int maxLimitMovementPerMonth;
  private int movementPerMonth;
  private double commission;
}