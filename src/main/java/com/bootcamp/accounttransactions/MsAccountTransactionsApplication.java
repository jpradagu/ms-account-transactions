package com.bootcamp.accounttransactions;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class MsAccountTransactionsApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsAccountTransactionsApplication.class, args);
	}

}
