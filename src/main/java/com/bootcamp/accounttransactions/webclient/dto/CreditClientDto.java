package com.bootcamp.accounttransactions.webclient.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CreditClientDto {
    private String id;
    private String code;
    private Float  creditAmount;
    private Float interestRate;
    private Client client;
    private CreditType creditType;
    private BankAccount bankAccount;
    private boolean state;
}
