package com.bootcamp.accounttransactions.webclient.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PersonClientAccountDto {
    private String id;
    private String code;
    private String accountNumber;
    private LocalDateTime openingDate;
    private TypeAccount typeAccount;
    private Client client;
    private Float balance;
    private boolean state;
}
