package com.bootcamp.accounttransactions.service;

import com.bootcamp.accounttransactions.entity.MovementRecord;
import com.bootcamp.accounttransactions.repository.IMovementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class MovementRecordService implements IMovementRecordService {

    @Autowired
    private IMovementRepository movementRepository;

    @Override
    public Mono<MovementRecord> save(MovementRecord movementRecord) {
        return movementRepository.save(movementRecord);
    }

    @Override
    public Mono<Void> deleteById(String id) {
        return movementRepository.deleteById(id);
    }

    @Override
    public Mono<MovementRecord> findById(String id) {
        return movementRepository.findById(id);
    }

    @Override
    public Flux<MovementRecord> findAll() {
        return movementRepository.findAll();
    }

    @Override
    public Mono<Long> countMovementsByAccountNameDocumentNumberDocumentTypeAndDates(String accountName, String originDocumentNumber, String originDocumentType, LocalDate createdAtFrom, LocalDate createdAtTo) {
        return movementRepository.countMovementRecordByAccountNameIsAndOriginDocumentNumberAndOriginDocumentTypeAndCreatedAtIsBetween(accountName, originDocumentNumber, originDocumentType, createdAtFrom, createdAtTo);
    }

	@Override
	public Flux<MovementRecord> saveAll(List<MovementRecord> movementRecords) {
		// TODO Auto-generated method stub
		return movementRepository.saveAll(movementRecords);
	}
}
