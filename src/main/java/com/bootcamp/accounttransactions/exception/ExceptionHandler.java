package com.bootcamp.accounttransactions.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@RestControllerAdvice
@Slf4j
public class ExceptionHandler {

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @org.springframework.web.bind.annotation.ExceptionHandler(value = Exception.class)
    public Mono<ExceptionResponse> manageModelException(Exception e) {
        log.error("Encountered exception: {}", e);
        return Mono.just(new ExceptionResponse(LocalDateTime.now(), e.getMessage()));
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @org.springframework.web.bind.annotation.ExceptionHandler(value = ModelNotFoundException.class)
    public Mono<ExceptionResponse> manageModelException(ModelNotFoundException e) {
        log.error("Model not found: {}", e);
        return Mono.just(new ExceptionResponse(LocalDateTime.now(), e.getMessage()));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @org.springframework.web.bind.annotation.ExceptionHandler(value = InsufficientBalanceException.class)
    public Mono<ExceptionResponse> manageInsufficientBalanceException(InsufficientBalanceException e) {
        log.error("Insufficient Balance: {}", e);
        return Mono.just(new ExceptionResponse(LocalDateTime.now(), e.getMessage()));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @org.springframework.web.bind.annotation.ExceptionHandler(value = AccountNotFoundException.class)
    public Mono<ExceptionResponse> manageAccountNotFoundException(AccountNotFoundException e) {
        return Mono.just(new ExceptionResponse(LocalDateTime.now(), e.getMessage()));
    }

}
