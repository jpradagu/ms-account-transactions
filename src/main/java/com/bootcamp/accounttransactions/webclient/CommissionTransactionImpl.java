package com.bootcamp.accounttransactions.webclient;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import com.bootcamp.accounttransactions.exception.CommissionNotCreateException;
import com.bootcamp.accounttransactions.webclient.dto.CommissionTransactionDto;

import reactor.core.publisher.Mono;

@Service
public class CommissionTransactionImpl implements ICommissionTransactionService{

	private static final String BASE_URL = "lb://ms-commission-transactions";
	
	@Autowired
	private WebClient.Builder webClient;
	

	@Override
	public Mono<CommissionTransactionDto> create(CommissionTransactionDto commissionDto) {
		return webClient.baseUrl(BASE_URL).build().post()
				.uri("/comission/transaction")
				.contentType(MediaType.APPLICATION_JSON)
				.bodyValue(commissionDto).retrieve()
				.bodyToMono(CommissionTransactionDto.class)
				.onErrorResume(error -> {
					WebClientResponseException response = (WebClientResponseException) error;
					if(response.getStatusCode() == HttpStatus.BAD_REQUEST) {
						return Mono.error(new CommissionNotCreateException());
					}
					return Mono.error(error);
				});
	}
	
}
