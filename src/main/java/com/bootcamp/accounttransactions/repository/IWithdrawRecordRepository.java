package com.bootcamp.accounttransactions.repository;

import com.bootcamp.accounttransactions.entity.MovementRecord;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IWithdrawRecordRepository extends ReactiveMongoRepository<MovementRecord, String> {
}
