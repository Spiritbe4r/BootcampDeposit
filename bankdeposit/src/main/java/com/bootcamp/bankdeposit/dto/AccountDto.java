package com.bootcamp.bankdeposit.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonDeserialize
public class AccountDto {
	private String id;
	private Double balance;

	private String accountNumber;
	private String accountType;

	private String currency;
	private String canBeDeposit;

	private int maxLimitMovementPerMonth;
	private int movementPerMonth;

}
