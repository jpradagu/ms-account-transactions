package com.bootcamp.accounttransactions.controller;

import com.bootcamp.accounttransactions.resource.TransferRecordResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/transfer")
public class TransferController {

    @Autowired
    private TransferRecordResource transferRecordResource;



}
