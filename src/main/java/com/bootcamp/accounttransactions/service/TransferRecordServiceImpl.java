package com.bootcamp.accounttransactions.service;

import com.bootcamp.accounttransactions.entity.TransferRecord;
import com.bootcamp.accounttransactions.repository.ITransferRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class TransferRecordServiceImpl implements ITransferRecordService {

    @Autowired
    private ITransferRecordRepository transferRecordRepository;

    @Override
    public Mono<TransferRecord> save(TransferRecord transferRecord) {
        return transferRecordRepository.save(transferRecord);
    }

    @Override
    public Mono<Void> deleteById(String id) {
        return transferRecordRepository.deleteById(id);
    }

    @Override
    public Mono<TransferRecord> findById(String id) {
        return transferRecordRepository.findById(id);
    }

    @Override
    public Flux<TransferRecord> findAll() {
        return transferRecordRepository.findAll();
    }
}
