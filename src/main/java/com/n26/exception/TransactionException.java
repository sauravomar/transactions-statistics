package com.n26.exception;

public class TransactionException extends Exception {

	/**
	* 
	*/
	private static final long serialVersionUID = -213778617673967657L;

	public TransactionException(String errorMessage) {
		super(errorMessage);
	}

	public TransactionException(String errorMessage, Throwable ex) {
		super(errorMessage, ex);
	}

}
