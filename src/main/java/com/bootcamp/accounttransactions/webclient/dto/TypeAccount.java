package com.bootcamp.accounttransactions.webclient.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TypeAccount {
    private String name;
    private Integer maxMonthlyMovements;
    private Integer limitWithoutCommission;
    private Float commissionTransaction;
}
