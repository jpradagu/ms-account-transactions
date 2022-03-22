package com.bootcamp.accounttransactions.service;

import com.bootcamp.accounttransactions.entity.MovementRecord;
import com.bootcamp.accounttransactions.util.ICRUD;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface IMovementRecordService extends ICRUD<MovementRecord, String> {

    Mono<Long> countMovementsByAccountNameDocumentNumberDocumentTypeAndDates(String accountName,
                                                                                String originDocumentNumber,
                                                                                String originDocumentType,
                                                                                LocalDate createdAtFrom,
                                                                                LocalDate createdAtTo);
    
    Flux<MovementRecord> saveAll(List<MovementRecord> movementRecords);
}
