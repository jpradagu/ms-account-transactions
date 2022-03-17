package com.bootcamp.accounttransactions.webclient.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CreditCardClientDto {
    private String id;
    private String code;
    private Float amountLimit;
    private Float interest;
    private LocalDateTime openingDate;
    private LocalDateTime deliveryDate;
    private String state;
    private Client client;
    private CreditCard creditCard;
}
