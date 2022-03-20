package com.bootcamp.accounttransactions.resource;

import com.bootcamp.accounttransactions.dto.MovementDto;
import com.bootcamp.accounttransactions.dto.TransactionDto;
import com.bootcamp.accounttransactions.dto.TransferDto;
import com.bootcamp.accounttransactions.entity.MovementRecord;
import com.bootcamp.accounttransactions.entity.TransferRecord;
import com.bootcamp.accounttransactions.exception.InsufficientBalanceException;
import com.bootcamp.accounttransactions.service.ITransferRecordService;
import com.bootcamp.accounttransactions.util.MapperUtil;
import com.bootcamp.accounttransactions.util.TransferCreationService;
import com.bootcamp.accounttransactions.webclient.dto.CompanyClientAccountDto;
import com.bootcamp.accounttransactions.webclient.dto.PersonClientAccountDto;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;

public class TransferRecordResource extends MapperUtil {

    @Autowired
    private ITransferRecordService transferRecordService;

    @Autowired
    private TransferCreationService transferCreationService;

    public Mono<TransactionDto> createMovement(TransferDto transferDto) {

        Mono<TransferRecord> monoTransfer;

        switch (transferDto.getTransferType()) {
            case THIRD_TRANSFERS:
            case SELF_TRANSFER:
                monoTransfer = transferCreationService.selfBankTransfer(map(transferDto, TransferRecord.class));
                break;
            case OTHER_BANKS_TRANSFER:
                monoTransfer = this.transferCreationService.otherBanksTransfer(map(transferDto, TransferRecord.class));
                break;
            default:
                return Mono.error(new Exception("Not Supported Transfer Type"));
        }

        return monoTransfer.map(x -> {
            TransactionDto transactionDto = new TransactionDto();
            transactionDto.setTransactionId(x.getId());
            transactionDto.setOriginAccount(x.getOriginAccount());

            return transactionDto;
        }).onErrorResume(Mono::error);
    }

    public Mono<TransferDto> update(TransferDto transferDto) {

        return transferRecordService.findById(transferDto.getTransactionId())
                .switchIfEmpty(Mono.error(new Exception()))
                .flatMap(x -> {
                    TransferRecord transferRecord = map(transferDto, TransferRecord.class);
                    transferRecord.setCreatedAt(x.getCreatedAt());
                    transferRecord.setUpdatedAt(LocalDateTime.now());

                    return transferRecordService.save(transferRecord).map(y -> map(y, TransferDto.class));
                });
    }

    public Mono<Void> delete(TransferDto transferDto) {
        return transferRecordService.findById(transferDto.getTransactionId())
                .switchIfEmpty(Mono.error(new Exception()))
                .flatMap(x -> transferRecordService.deleteById(transferDto.getTransactionId()));
    }

    public Mono<MovementDto> findById(String id) {
        return transferRecordService.findById(id).map(x -> map(x, MovementDto.class));
    }

    public Flux<MovementDto> findAll() {

        return transferRecordService.findAll()
                .map(x -> map(x, MovementDto.class));
    }
}
