package com.bootcamp.accounttransactions.dto;

import com.bootcamp.accounttransactions.util.types.TransferType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransferDto extends TransactionDto {

    private String recipientAccount;

    private String recipientDocumentNumber;

    private String recipientDocumentType;

    private String recipientClientType;

    private TransferType transferType;
}
