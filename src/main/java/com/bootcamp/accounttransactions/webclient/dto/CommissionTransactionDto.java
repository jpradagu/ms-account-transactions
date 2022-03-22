package com.bootcamp.accounttransactions.webclient.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CommissionTransactionDto {
    private String documentType;
    private String numberDocument;
    private String movementType;
    private Float balance;
    private Float commission;
}
