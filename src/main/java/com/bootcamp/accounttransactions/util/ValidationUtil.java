package com.bootcamp.accounttransactions.util;

import com.bootcamp.accounttransactions.dto.TransferDto;
import com.bootcamp.accounttransactions.webclient.IRegisterProductService;
import com.bootcamp.accounttransactions.webclient.dto.CompanyClientAccountDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class ValidationUtil {

    @Autowired
    private IRegisterProductService registerProductService;


    private Mono<?> validateAccountExists(TransferDto transferDto) {

        switch (transferDto.getClientType()) {
            case "BUSINESS":
                return registerProductService.findPersonalAccountByDocumentAndDocumentTypeAndAccount(transferDto.getOriginDocumentNumber(),
                            transferDto.getOriginDocumentType(), transferDto.getAccountName())
                    .switchIfEmpty(Mono.error(new Exception("Account Type Not Found")))
                    .onErrorResume(Mono::error);
            case "PERSONAL":
                return registerProductService.findCompanyClientAccountByDocumentAndDocumentTypeAndAccount(transferDto.getOriginDocumentNumber(),
                                transferDto.getOriginDocumentType(), transferDto.getAccountName())
                        .switchIfEmpty(Mono.error(new Exception("Account Type Not Found")))
                        .onErrorResume(Mono::error);
            default:
                return Mono.error(new Exception("Client Type Not Supported"));

        }


    }




}
