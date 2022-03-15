package com.bootcamp.accounttransactions.webclient;

import com.bootcamp.accounttransactions.webclient.dto.CompanyClientAccountDto;
import com.bootcamp.accounttransactions.webclient.dto.PersonClientAccountDto;
import reactor.core.publisher.Mono;

public interface IRegisterProductService {

    Mono<PersonClientAccountDto> findPersonalAccountByDocumentAndDocumentTypeAndAccount(String document,
                                                                                        String documentType,
                                                                                        String account);

    Mono<CompanyClientAccountDto> findCompanyClientAccountByDocumentAndDocumentTypeAndAccount(String document,
                                                                                                     String documentType,
                                                                                                     String account);
}
