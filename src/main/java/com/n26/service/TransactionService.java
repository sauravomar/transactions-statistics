package com.n26.service;

import java.util.List;

import com.n26.dto.TransactionDto;
import com.n26.enums.ResponseEnum;
import com.n26.exception.TransactionException;
import com.n26.model.Transactions;

public interface TransactionService {
	public ResponseEnum newTransactions(TransactionDto transactionDto) throws Exception;
	public void deleteTransactions();
	public List<Transactions>getTransactions();
	
}
