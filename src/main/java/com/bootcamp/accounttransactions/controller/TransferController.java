package com.bootcamp.accounttransactions.controller;

import com.bootcamp.accounttransactions.dto.MovementDto;
import com.bootcamp.accounttransactions.dto.TransactionDto;
import com.bootcamp.accounttransactions.dto.TransferDto;
import com.bootcamp.accounttransactions.resource.TransferRecordResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/transfer")
public class TransferController {

    @Autowired
    private TransferRecordResource transferRecordResource;

    @PostMapping
    public Mono<TransactionDto> create(@RequestBody TransferDto transferDto) {
        return transferRecordResource.create(transferDto);
    }


}
