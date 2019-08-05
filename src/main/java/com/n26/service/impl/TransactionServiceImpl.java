package com.n26.service.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.n26.dto.TransactionDto;
import com.n26.enums.ResponseEnum;
import com.n26.model.Transactions;
import com.n26.service.TransactionService;
import com.n26.service.ValidationService;
import com.n26.util.DateParseUtil;

import lombok.extern.slf4j.Slf4j;

@Service("transactionService")
@Slf4j
@lombok.Getter
@lombok.Setter
public class TransactionServiceImpl implements TransactionService {

	private List<Transactions> transactions = new ArrayList<Transactions>();

	private ValidationService validationService;

	private DateParseUtil dateUtil;

	@Autowired
	public TransactionServiceImpl(ValidationService validationService, DateParseUtil dateUtil) {
		this.validationService = validationService;
		this.dateUtil = dateUtil;
	}

	public ResponseEnum newTransactions(TransactionDto transactionDto) throws Exception {
		log.info(" New Transaction received {}  ", transactionDto);

		// validate empty object
		if (transactionDto.getAmount().isEmpty() || transactionDto.getTimestamp() == null 
				|| !getValidationService().validateAmount(transactionDto.getAmount()) 
				|| !getValidationService().validateDateFormat(transactionDto))
			return ResponseEnum.NOT_PARSEABLE;
		
		Transactions transaction = getTransaction(transactionDto);

		// validate future date
		boolean isValid = getValidationService().validateFutureTime(transaction);
		if (!isValid)
			return ResponseEnum.NOT_PARSEABLE;

		isValid = getValidationService().validateTime(transaction);
		if (!isValid)
			return ResponseEnum.NO_CONTENT;

		getTransactions().add(transaction);
		return ResponseEnum.CREATED;

	}

	public void deleteTransactions() {
		log.info(" Deleting Transactions ");
		transactions.clear();
	}

	private Transactions getTransaction(TransactionDto dto) throws ParseException {
		return new Transactions(Double.parseDouble(dto.getAmount()),
				getDateUtil().parseDateFromString(dto.getTimestamp()));

	}
	
	
}
