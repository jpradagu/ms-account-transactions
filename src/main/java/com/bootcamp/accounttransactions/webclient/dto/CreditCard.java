package com.bootcamp.accounttransactions.webclient.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CreditCard {
    private String name;
    private String creditCardNumber;
    private Float maintenance;
}
