package com.bootcamp.accounttransactions.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Getter
@Setter
public abstract class BaseEntity {

    @Id
    private String id;

    private Integer amount;

    private String accountName;

    private String originAccount;

    private String originDocumentNumber;

    private String originDocumentType;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
