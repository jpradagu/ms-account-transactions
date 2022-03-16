package com.bootcamp.accounttransactions.resource;

import com.bootcamp.accounttransactions.dto.MovementDto;
import com.bootcamp.accounttransactions.dto.TransactionDto;
import com.bootcamp.accounttransactions.entity.MovementRecord;
import com.bootcamp.accounttransactions.exception.InsufficientBalanceException;
import com.bootcamp.accounttransactions.exception.ModelNotFoundException;
import com.bootcamp.accounttransactions.service.IMovementRecordService;
import com.bootcamp.accounttransactions.util.MapperUtil;
import com.bootcamp.accounttransactions.webclient.IRegisterProductService;
import com.bootcamp.accounttransactions.webclient.dto.CompanyClientAccountDto;
import com.bootcamp.accounttransactions.webclient.dto.PersonClientAccountDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Service
public class MovementRecordResource extends MapperUtil {

    @Autowired
    private IMovementRecordService movementRecordService;

    @Autowired
    private IRegisterProductService registerProductService;

    private Mono<TransactionDto> setDataAndSave(MovementDto movementDto) {
        MovementRecord movementRecord = new MovementRecord();
        movementRecord.setAmount(movementDto.getAmount());
        movementRecord.setOriginAccount(movementDto.getOriginAccount());
        movementRecord.setOriginDocumentNumber(movementDto.getOriginDocumentNumber());
        movementRecord.setOriginDocumentType(movementDto.getOriginDocumentType());
        movementRecord.setMovementType(movementDto.getMovementType());
        movementRecord.setMovementShape(movementDto.getMovementShape());
        movementRecord.setMovementOriginName(movementDto.getMovementOriginName());
        movementRecord.setCreatedAt(LocalDateTime.now());

        return movementRecordService.save(movementRecord).map(y -> {
            TransactionDto transactionDto = new TransactionDto();
            transactionDto.setTransactionId(y.getId());
            transactionDto.setOriginAccount(y.getOriginAccount());

            return transactionDto;
        });
    }

    private Mono<TransactionDto> validateBalancePersonalAccount(PersonClientAccountDto personClientAccountDto,
                                                                MovementDto movementDto) {
        Float newBalance = personClientAccountDto.getBalance();
        switch (movementDto.getMovementType().toUpperCase()) {
            case "DEPOSIT":
                newBalance = movementDto.getAmount() + newBalance;
                break;
            case "WITHDRAWAL":
                newBalance = personClientAccountDto.getBalance() - movementDto.getAmount();

                if(personClientAccountDto.getBalance() < movementDto.getAmount()) {
                    return Mono.error(new InsufficientBalanceException());
                }

                break;
            default:
                return Mono.error(new Exception("Unsupported Movement Type"));
        }

        personClientAccountDto.setBalance(newBalance);

        return registerProductService.updatePersonalAccount(personClientAccountDto)
                .flatMap(y -> setDataAndSave(movementDto).map(z -> z));
    }

    private Mono<TransactionDto> validateBalanceCompanyAccount(CompanyClientAccountDto companyClientAccountDto,
                                                                MovementDto movementDto) {
        Float newBalance = companyClientAccountDto.getBalance();
        switch (movementDto.getMovementType().toUpperCase()) {
            case "DEPOSIT":
                newBalance += movementDto.getAmount();
                break;
            case "WITHDRAWAL":
                newBalance -= companyClientAccountDto.getBalance();

                if(companyClientAccountDto.getBalance() < movementDto.getAmount()) {
                    return Mono.error(new InsufficientBalanceException());
                }

                break;
            default:
                return Mono.error(new Exception("Unsupported Movement Type"));
        }

        companyClientAccountDto.setBalance(newBalance);

        return registerProductService.updateCompanyAccount(companyClientAccountDto)
                .flatMap(y -> setDataAndSave(movementDto).map(z -> z));
    }

    public Mono<TransactionDto> createMovement(MovementDto movementDto) {

        /*
        * BUSCO SALDOS POR ACCOUNT Y DATOS DEL CLIENTE
        * SI EL AMOUNT ES MAYOR O IGUAL AL SALDO, REALIZO TRANSACCIÓN
        * DE LO CONTRARIO THROW EXCEPTION
        * */

        switch (movementDto.getClientType().toUpperCase()) {
            case "PERSONAL":
                return registerProductService.findPersonalAccountByDocumentAndDocumentTypeAndAccount(movementDto.getOriginDocumentNumber(),
                                movementDto.getOriginDocumentType(), movementDto.getOriginAccount())
                        .switchIfEmpty(Mono.error(new Exception()))
                        .onErrorResume(Mono::error)
                        .flatMap(x -> validateBalancePersonalAccount(x, movementDto).onErrorResume(Mono::error));
            case "BUSINESS":
                return registerProductService.findCompanyClientAccountByDocumentAndDocumentTypeAndAccount(movementDto.getOriginDocumentNumber(),
                                movementDto.getOriginDocumentType(), movementDto.getOriginAccount())
                        .switchIfEmpty(Mono.error(new Exception()))
                        .flatMap(x -> {
                            if(x.getBalance() < movementDto.getAmount()) {
                                return Mono.error(new InsufficientBalanceException());
                            }
                                // UPDATE BALANCE AND SAVE TRANSACTION
                            return setDataAndSave(movementDto);
                        });
            default:
                return Mono.error(new Exception("Unsuportted client type"));
        }
    }

    public Mono<MovementDto> update(MovementDto movementDto) {

        return movementRecordService.findById(movementDto.getTransactionId())
                .switchIfEmpty(Mono.error(new Exception()))
                .flatMap(x -> {
                    MovementRecord movementRecord = map(movementDto, MovementRecord.class);
                    movementRecord.setCreatedAt(x.getCreatedAt());
                    movementRecord.setUpdatedAt(LocalDateTime.now());

                    return movementRecordService.save(movementRecord).map(y -> map(y, MovementDto.class));
                });
    }

    public Mono<Void> delete(MovementDto movementDto) {
        return movementRecordService.findById(movementDto.getTransactionId())
                .switchIfEmpty(Mono.error(new Exception()))
                .flatMap(x -> movementRecordService.deleteById(movementDto.getTransactionId()));
    }

    public Mono<MovementDto> findById(String id) {
        return movementRecordService.findById(id).map(x -> map(x, MovementDto.class));
    }

    public Flux<MovementDto> findAll() {

        return movementRecordService.findAll()
                .map(x -> map(x, MovementDto.class));
    }
}
