package com.bootcamp.accounttransactions.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovementDto extends TransactionDto {

    private String movementShape;

    private String movementOriginName;

}
