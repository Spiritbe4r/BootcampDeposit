package com.bootcamp.bankdeposit.models.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "account")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account {
  @Id
  private String id;
  private Double balance;
  private String currency;
  private String idClient;
  private String accountNumber;

  private String canBeDeposit;
  /**
   * maxLimitMovementPerMonth: indica la cantidad maxima de veces que se puede depositar
   * X: cantidad maxima de despositos mensuales
   * 0: no se puede depositar
   */

  private int maxLimitMovementPerMonth;
  /**
   * accountType: indica  el tipo de cuenta
   * 1: Cuenta de Ahorro
   * 2: Cuenta Corriente
   * 3: Plazo fijo
   */

  private String accountType;
  /**
   * limitCounter: acumula la ncatidad de depositos que se van realizando
   */
  private int movementPerMonth;
}
