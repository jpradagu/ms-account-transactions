package com.bootcamp.accounttransactions.controller;

import com.bootcamp.accounttransactions.dto.MovementDto;
import com.bootcamp.accounttransactions.dto.TransactionDto;
import com.bootcamp.accounttransactions.resource.MovementRecordResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/movements")
public class MovementController {

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


}
