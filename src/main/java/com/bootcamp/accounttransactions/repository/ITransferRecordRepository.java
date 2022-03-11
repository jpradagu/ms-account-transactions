package com.bootcamp.accounttransactions.repository;

import com.bootcamp.accounttransactions.entity.TransferRecord;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface ITransferRecordRepository extends ReactiveMongoRepository<TransferRecord, String> {
}
