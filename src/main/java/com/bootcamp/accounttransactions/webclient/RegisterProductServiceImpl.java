package com.bootcamp.accounttransactions.webclient;

import com.bootcamp.accounttransactions.exception.AccountNotFoundException;
import com.bootcamp.accounttransactions.webclient.dto.CompanyClientAccountDto;
import com.bootcamp.accounttransactions.webclient.dto.PersonClientAccountDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

@Service
public class RegisterProductServiceImpl implements IRegisterProductService {

    private static final String BASE_URL = "lb://ms-register-product-client";

    @Autowired
    private WebClient.Builder webClient;

    @Override
    public Mono<PersonClientAccountDto> findPersonalAccountByDocumentAndDocumentTypeAndAccount(String document, String documentType, String account) {
            return webClient.baseUrl(BASE_URL).build().get().uri( "/api/account/person/document/".concat(document).concat("/type/")
                            .concat(documentType).concat("/account/").concat(account))
                    .retrieve()
                    .bodyToMono(PersonClientAccountDto.class)
                    .onErrorResume(error -> {
                        WebClientResponseException response = (WebClientResponseException) error;

                        if(response.getStatusCode() == HttpStatus.NOT_FOUND) {
                            return Mono.error(new AccountNotFoundException());
                        }

                        return Mono.error(error);
                    });

    }

    public Mono<CompanyClientAccountDto> findCompanyClientAccountByDocumentAndDocumentTypeAndAccount(String document,
                                                                                                     String documentType, String account) {
        return webClient.baseUrl(BASE_URL).build().get().uri( "/api/account/company/document/".concat(document).concat("/type/")
                        .concat(documentType).concat("/account/").concat(account))
                        .retrieve().bodyToMono(CompanyClientAccountDto.class)
                        .onErrorResume(error -> {
                            WebClientResponseException response = (WebClientResponseException) error;

                            if(response.getStatusCode() == HttpStatus.NOT_FOUND) {
                                return Mono.error(new AccountNotFoundException());
                            }

                            return Mono.error(error);
                        });
    }

    @Override
    public Mono<PersonClientAccountDto> updatePersonalAccount(PersonClientAccountDto personClientAccountDto) {
        return webClient.baseUrl(BASE_URL).build().put().uri("/api/account/person").contentType(MediaType.APPLICATION_JSON)
                .bodyValue(personClientAccountDto).retrieve().bodyToMono(PersonClientAccountDto.class)
                .onErrorResume(error -> {
                    WebClientResponseException response = (WebClientResponseException) error;

                    if(response.getStatusCode() == HttpStatus.NOT_FOUND) {
                        return Mono.error(new AccountNotFoundException());
                    }

                    return Mono.error(error);
                });
    }

    @Override
    public Mono<CompanyClientAccountDto> updateCompanyAccount(CompanyClientAccountDto companyClientAccountDto) {
        return webClient.baseUrl(BASE_URL).build().put().uri("/api/account/company").contentType(MediaType.APPLICATION_JSON)
                .bodyValue(companyClientAccountDto).retrieve().bodyToMono(CompanyClientAccountDto.class)
                .onErrorResume(error -> {
                    WebClientResponseException response = (WebClientResponseException) error;

                    if(response.getStatusCode() == HttpStatus.NOT_FOUND) {
                        return Mono.error(new AccountNotFoundException());
                    }

                    return Mono.error(error);
                });
    }

}
