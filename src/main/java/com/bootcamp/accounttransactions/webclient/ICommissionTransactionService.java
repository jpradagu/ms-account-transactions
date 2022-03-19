package com.bootcamp.accounttransactions.webclient;

import org.springframework.stereotype.Service;

import com.bootcamp.accounttransactions.webclient.dto.CommissionTransactionDto;

import reactor.core.publisher.Mono;

@Service
public interface ICommissionTransactionService {
	public Mono<CommissionTransactionDto> create(CommissionTransactionDto commissionDto);
}
