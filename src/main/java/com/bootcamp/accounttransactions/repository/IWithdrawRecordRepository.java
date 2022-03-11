package com.bootcamp.accounttransactions.repository;

import com.bootcamp.accounttransactions.entity.MovementRecord;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface IWithdrawRecordRepository extends ReactiveMongoRepository<MovementRecord, String> {
}
