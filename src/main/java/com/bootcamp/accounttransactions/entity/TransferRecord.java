package com.bootcamp.accounttransactions.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document
public class TransferRecord extends BaseEntity {

    private String recipientAccount;

    private String recipientDocumentNumber;

    private String recipientDocumentType;
}
