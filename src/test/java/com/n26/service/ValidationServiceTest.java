package com.n26.service;

import java.time.Instant;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.n26.dto.TransactionDto;
import com.n26.model.Transactions;
import com.n26.service.impl.ValidationServiceImpl;
import com.n26.util.DateParseUtil;

import junit.framework.Assert;

@SuppressWarnings("deprecation")
public class ValidationServiceTest {
	
	
	private ValidationServiceImpl validationSvc;
	
	@Mock
	private DateParseUtil dateUtil;

	private Transactions transactions;
	
	private TransactionDto transactionDto;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		validationSvc = Mockito.spy(new ValidationServiceImpl(dateUtil));
		transactions = new Transactions(123.23, Date.from(Instant.now()));
		transactionDto = new TransactionDto("123.23", Instant.now().toString());
	}
	
	@Test
	public void validateTrueTimeTest(){
		boolean isValid =  validationSvc.validateTime(transactions);
		Assert.assertEquals(true, isValid);
	}
	
	@Test
	public void validateFalseTimeTest(){
		transactions.setTimestamp(Date.from(Instant.now().minusSeconds(61)));
		boolean isValid =  validationSvc.validateTime(transactions);
		Assert.assertEquals(false, isValid);
	}
	
	@Test
	public void validateFutureTimeTest(){
		transactions.setTimestamp(Date.from(Instant.now().plusSeconds(60)));
		boolean isValid =  validationSvc.validateFutureTime(transactions);
		Assert.assertEquals(false, isValid);
	}
	
	@Test
	public void validateDateFormatTest(){
		boolean isValid =  validationSvc.validateDateFormat(transactionDto);
		Assert.assertEquals(true, isValid);
	}
	
	@Test
	public void validateAmountTest(){
		boolean isValid =  validationSvc.validateAmount(transactionDto.getAmount());
		Assert.assertEquals(true, isValid);
	}
	
	
}
