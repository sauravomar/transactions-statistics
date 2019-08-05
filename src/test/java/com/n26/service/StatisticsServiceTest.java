package com.n26.service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.n26.dto.StatisticsDto;
import com.n26.model.Transactions;
import com.n26.service.impl.StatisticsServiceImpl;
import com.n26.service.impl.TransactionServiceImpl;
import com.n26.service.impl.ValidationServiceImpl;
import com.n26.util.DateParseUtil;

import junit.framework.Assert;

@SuppressWarnings("deprecation")
public class StatisticsServiceTest {


	private StatisticsServiceImpl statsSvc;
	
	private TransactionServiceImpl transSvc;
	
	@Mock
	private DateParseUtil dateUtil;
	
	@Mock
	private ValidationServiceImpl validationServiceImpl;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		transSvc = Mockito.spy(new TransactionServiceImpl(validationServiceImpl,dateUtil));
		statsSvc = Mockito.spy(new StatisticsServiceImpl(transSvc));
	}
	
	@Test
	public void  getStatisticsDtoTest() {
		StatisticsDto statsDto = new StatisticsDto(2, "124.23", "123.23", "247.46", "123.73");
		Transactions transactions1 = new Transactions(123.23, Date.from(Instant.now()));
		Transactions transactions2 = new Transactions(124.23, Date.from(Instant.now()));
		
		List<Transactions>list = new ArrayList<Transactions>();
		list.add(transactions1);
		list.add(transactions2);
		
		transSvc.setTransactions(list);
		
		StatisticsDto result =  statsSvc.getStatisticsDto();
		
		Assert.assertEquals(statsDto.getCount(), result.getCount());
		Assert.assertEquals(statsDto.getMax(), result.getMax());
		Assert.assertEquals(statsDto.getMin(), result.getMin());
		Assert.assertEquals(statsDto.getSum(), result.getSum());
		Assert.assertEquals(statsDto.getAvg(), result.getAvg());
	}
	
	
}
