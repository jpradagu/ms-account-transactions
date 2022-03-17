package com.bootcamp.accounttransactions.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovementRecord extends BaseEntity {

    private String movementType;

    private String movementShape;

    private String movementOriginName;
}
