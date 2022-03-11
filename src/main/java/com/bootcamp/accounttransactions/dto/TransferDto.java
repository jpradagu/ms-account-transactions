package com.bootcamp.accounttransactions.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransferDto {

    private String recipientAccount;

    private String recipientDocumentNumber;

    private String recipientDocumentType;
}
