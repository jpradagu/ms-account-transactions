package com.bootcamp.accounttransactions.controller;

import com.bootcamp.accounttransactions.dto.MovementDto;
import com.bootcamp.accounttransactions.dto.TransactionDto;
import com.bootcamp.accounttransactions.resource.MovementRecordResource;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/movements")
public class MovementController {
	
	private static final Logger logger = LogManager.getLogger(MovementController.class);

    @Autowired
    private MovementRecordResource movementRecordResource;

    @GetMapping
    public Flux<MovementDto> findAll() {
        return movementRecordResource.findAll();
    }

    @GetMapping("/{id}")
    public Mono<MovementDto> findById(@PathVariable String id) {
        return movementRecordResource.findById(id);
    }

    @PostMapping
    @CircuitBreaker(name="allCB", fallbackMethod = "fallbackCreateMovement")
    public Flux<TransactionDto> createMovement(@RequestBody MovementDto movementDto) {
        return movementRecordResource.createMovement(movementDto);
    }

    @PutMapping
    public Mono<MovementDto> updateMovement(@RequestBody MovementDto movementDto) {
        return movementRecordResource.update(movementDto);
    }

    @DeleteMapping
    private Mono<Void> delete(@RequestBody MovementDto movementDto) {
        return movementRecordResource.delete(movementDto);
    }
    
    public Flux<TransactionDto> fallbackCreateMovement(@RequestBody MovementDto movementDto, RuntimeException e){
    	logger.error("services commission y register product is down");
    	return Flux.just(movementDto);
    }


}
