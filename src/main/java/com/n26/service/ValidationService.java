package com.n26.service;

import com.n26.dto.TransactionDto;
import com.n26.model.Transactions;

public interface ValidationService {
	public boolean validateTime(Transactions  dto);
	public boolean validateFutureTime(Transactions  dto);
	public boolean validateDateFormat(TransactionDto dto);
	public boolean validateAmount(String amount);
}
