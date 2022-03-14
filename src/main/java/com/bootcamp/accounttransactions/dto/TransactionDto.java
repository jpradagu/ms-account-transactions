package com.bootcamp.accounttransactions.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TransactionDto {

    private Float amount;

    private String accountName;

    private String originAccount;

    private String originDocumentNumber;

    private String originDocumentType;

    private String transactionId;

    private LocalDateTime transactionDate;
}
