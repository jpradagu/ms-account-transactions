package com.bootcamp.accounttransactions.exception;

import lombok.Data;

@Data
public class InsufficientBalanceException extends RuntimeException {

    private static final String MESSAGE = "Insufficient balance in account for transaction";

    public InsufficientBalanceException() {
        super(MESSAGE);
    }

}
