package com.n26.service;

import java.time.Instant;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.n26.dto.TransactionDto;
import com.n26.enums.ResponseEnum;
import com.n26.model.Transactions;
import com.n26.service.impl.TransactionServiceImpl;
import com.n26.service.impl.ValidationServiceImpl;
import com.n26.util.DateParseUtil;

import junit.framework.Assert;

@SuppressWarnings("deprecation")
public class TransactionServiceTest {

	private TransactionServiceImpl transService;
	
	@Mock
	private DateParseUtil dateUtil;
	
	@Mock
	private ValidationServiceImpl validationServiceImpl;

	private Transactions transactions;
	
	private TransactionDto transactionDto;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		transService = Mockito.spy(new TransactionServiceImpl(validationServiceImpl,dateUtil));
		transactions = new Transactions(123.23, Date.from(Instant.now()));
		transactionDto = new TransactionDto("123.23", Instant.now().toString());
	}
	
	@Test
	public void  deleteTransactionsTest() {
		transService.deleteTransactions();
	}
	
	@Test
	public void newTransactionsTest() throws Exception {
		
		Mockito.when(validationServiceImpl.validateDateFormat(transactionDto)).thenReturn(true);
		Mockito.when(validationServiceImpl.validateAmount(transactionDto.getAmount())).thenReturn(true);
		Mockito.when(validationServiceImpl.validateFutureTime(Mockito.any())).thenReturn(true);
		Mockito.when(validationServiceImpl.validateTime(Mockito.any())).thenReturn(true);
		Mockito.when(dateUtil.parseDateFromString(transactionDto.getTimestamp())).thenReturn(transactions.getTimestamp());

		
		ResponseEnum response = transService.newTransactions(transactionDto);
		Assert.assertEquals(ResponseEnum.CREATED, response);
	}
	
	
}
