package com.bootcamp.accounttransactions.exception;

public class CommissionNotCreateException extends RuntimeException{
	
	private static final String MESSAGE ="Commission cannot be created";
	
	public CommissionNotCreateException() {
		super(MESSAGE);
	}

}
