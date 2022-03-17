package com.bootcamp.accounttransactions.repository;

import com.bootcamp.accounttransactions.entity.MovementRecord;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Repository
public interface IMovementRepository extends ReactiveMongoRepository<MovementRecord, String> {

    Mono<Long> countMovementRecordByAccountNameIsAndOriginDocumentNumberAndOriginDocumentTypeAndCreatedAtIsBetween(String accountName,
                                                                                                                      String originDocumentNumber,
                                                                                                                      String originDocumentType,
                                                                                                                      LocalDate createdAtFrom,
                                                                                                                      LocalDate createdAtTo);
}
