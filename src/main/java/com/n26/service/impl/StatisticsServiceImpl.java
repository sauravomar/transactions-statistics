package com.n26.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.n26.dto.StatisticsDto;
import com.n26.model.Transactions;
import com.n26.service.StatisticsService;
import com.n26.service.TransactionService;

import lombok.extern.slf4j.Slf4j;

@Service("statisticsService")
@Slf4j
@lombok.Getter
@lombok.Setter
public class StatisticsServiceImpl implements StatisticsService {

	private TransactionService transactionService;

	@Autowired
	public StatisticsServiceImpl(TransactionService transactionService) {
		this.transactionService = transactionService;
	}

	public StatisticsDto getStatisticsDto() {
		log.info(" Get Statistics");
		return calculateStats(getTransactionsWithinTime(Instant.now().minusSeconds(60), Instant.now()));

	}

	private List<Transactions> getTransactionsWithinTime(Instant from, Instant to) {

		log.info(" Get transactions from {} , to {} ", from, to);

		List<Transactions> transactions = getTransactionService().getTransactions();

		transactions = transactions.stream()
				.filter(transaction -> transaction.getTimestamp().compareTo(Date.from(from)) > 0
						&& transaction.getTimestamp().compareTo(Date.from(to)) <= 0)
				.collect(Collectors.toList());

		return transactions;

	}

	private StatisticsDto calculateStats(List<Transactions> transactions) {

		if (transactions.isEmpty())
			return new StatisticsDto(0, "0.00", "0.00","0.00","0.00");

		Double max = 0.00;
		Double min = Double.MAX_VALUE;
		Double sum = 0.00;

		for (Transactions transaction : transactions) {

			if (transaction.getAmount() > max) {
				max = transaction.getAmount();
			}

			if (transaction.getAmount() < min) {
				min = transaction.getAmount();
			}

			sum = sum + transaction.getAmount();
		}

		Double avg = sum / transactions.size();

		return new StatisticsDto(transactions.size(), round(max), round(min), round(sum),
				round(avg));

	}

	private String round(Double val) {
		return new BigDecimal(val.toString()).setScale(2, RoundingMode.HALF_UP).toString();
	}


}
