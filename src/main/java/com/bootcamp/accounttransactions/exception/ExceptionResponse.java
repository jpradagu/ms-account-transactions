package com.bootcamp.accounttransactions.exception;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ExceptionResponse {

    private LocalDateTime timestamp;
    private String message;

    public ExceptionResponse(LocalDateTime timestamp, String message) {
        this.timestamp = timestamp;
        this.message = message;
    }
}
