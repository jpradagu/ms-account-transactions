package com.bootcamp.accounttransactions.service;

import com.bootcamp.accounttransactions.entity.MovementRecord;
import com.bootcamp.accounttransactions.repository.IWithdrawRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class MovementRecordService implements IMovementRecordService {

    @Autowired
    private IWithdrawRecordRepository withdrawRecordRepository;

    @Override
    public Mono<MovementRecord> save(MovementRecord movementRecord) {
        return withdrawRecordRepository.save(movementRecord);
    }

    @Override
    public Mono<Void> deleteById(String id) {
        return withdrawRecordRepository.deleteById(id);
    }

    @Override
    public Mono<MovementRecord> findById(String id) {
        return withdrawRecordRepository.findById(id);
    }

    @Override
    public Flux<MovementRecord> findAll() {
        return withdrawRecordRepository.findAll();
    }
}
