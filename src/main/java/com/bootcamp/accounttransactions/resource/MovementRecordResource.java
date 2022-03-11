package com.bootcamp.accounttransactions.resource;

import com.bootcamp.accounttransactions.dto.MovementDto;
import com.bootcamp.accounttransactions.dto.TransactionDto;
import com.bootcamp.accounttransactions.entity.MovementRecord;
import com.bootcamp.accounttransactions.service.IMovementRecordService;
import com.bootcamp.accounttransactions.util.MapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Service
public class MovementRecordResource extends MapperUtil {

    @Autowired
    private IMovementRecordService movementRecordService;

    public Mono<TransactionDto> createMovement(MovementDto movementDto) {

        /*
        * BUSCO SALDOS POR ACCOUNT Y DATOS DEL CLIENTE
        * SI EL AMOUNT ES MAYOR O IGUAL AL SALDO, REALIZO TRANSACCIÓN
        * DE LO CONTRARIO THROW EXCEPTION
        * */

        MovementRecord movementRecord = new MovementRecord();
        movementRecord.setAccountName(movementDto.getAccountName());
        movementRecord.setAmount(movementDto.getAmount());
        movementRecord.setOriginDocumentNumber(movementDto.getOriginDocumentNumber());
        movementRecord.setOriginDocumentType(movementDto.getOriginDocumentType());
        movementRecord.setMovementShape(movementDto.getMovementShape());
        movementRecord.setMovementOriginName(movementDto.getMovementOriginName());
        movementRecord.setCreatedAt(LocalDateTime.now());

        return movementRecordService.save(movementRecord).map(x -> {
            TransactionDto transactionDto = new TransactionDto();
            transactionDto.setTransactionId(x.getId());
            transactionDto.setOriginAccount(x.getOriginAccount());

            return transactionDto;
        });
    }

    public Mono<MovementDto> update(MovementDto movementDto) {

        return movementRecordService.findById(movementDto.getTransactionId())
                .switchIfEmpty(Mono.error(new Exception()))
                .flatMap(x -> {
                    MovementRecord creditCard = convertToEntity(movementDto);
                    creditCard.setCreatedAt(x.getCreatedAt());
                    creditCard.setUpdatedAt(LocalDateTime.now());

                    return movementRecordService.save(creditCard).map(y -> convertToDto(y));
                });
    }

    public Mono<Void> delete(MovementDto movementDto) {
        return movementRecordService.findById(movementDto.getTransactionId())
                .switchIfEmpty(Mono.error(new Exception()))
                .flatMap(x -> movementRecordService.deleteById(movementDto.getTransactionId()));
    }

    public Mono<MovementDto> findById(String id) {
        return movementRecordService.findById(id).map(x -> convertToDto(x));
    }

    public Flux<MovementDto> findAll() {

        return movementRecordService.findAll()
                .map(x -> convertToDto(x));
    }
}
