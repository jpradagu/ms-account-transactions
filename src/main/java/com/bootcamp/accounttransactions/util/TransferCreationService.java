package com.bootcamp.accounttransactions.util;

import com.bootcamp.accounttransactions.dto.TransferDto;
import com.bootcamp.accounttransactions.entity.TransferRecord;
import com.bootcamp.accounttransactions.service.ITransferRecordService;
import com.bootcamp.accounttransactions.util.types.TransferType;
import com.bootcamp.accounttransactions.webclient.IRegisterProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class TransferCreationService {

    @Autowired
    private ITransferRecordService transferRecordService;

    @Autowired
    private IRegisterProductService registerProductService;

    public Mono<TransferRecord> selfBankTransfer(TransferRecord transferRecord) {

        switch (transferRecord.getRecipientClientType()) {
            case "BUSINESS":
                return registerProductService.findCompanyClientAccountByDocumentAndDocumentTypeAndAccount(transferRecord.getRecipientDocumentNumber(),
                                transferRecord.getRecipientDocumentType(), transferRecord.getRecipientAccount())
                        .flatMap(x -> transferRecordService.save(transferRecord)).onErrorResume(Mono::error);
            case "PERSONAL":
                return registerProductService.findPersonalAccountByDocumentAndDocumentTypeAndAccount(transferRecord.getRecipientAccount(),
                        transferRecord.getRecipientDocumentType(), transferRecord.getRecipientAccount())
                        .flatMap(x -> transferRecordService.save(transferRecord)).onErrorResume(Mono::error);
            default:
                return Mono.error(new Exception("Client Type Not Supported"));
        }
    }

    public Mono<TransferRecord> otherBanksTransfer(TransferRecord transferRecord) {
        return this.transferRecordService.save(transferRecord);
    }

}
