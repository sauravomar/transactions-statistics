package com.n26.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.n26.dto.TransactionDto;
import com.n26.enums.ResponseEnum;
import com.n26.service.TransactionService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/transactions")
@lombok.Getter
@lombok.Setter
@Slf4j
@SuppressWarnings("rawtypes")
public class TransactionsController {

	private TransactionService transactionService;

	@Autowired
	public TransactionsController(TransactionService transactionService) {
		this.transactionService = transactionService;
	}

	@PostMapping
	public ResponseEntity addTransactions(@RequestBody TransactionDto transactionDto) throws Exception {

		log.info(" Request received for add transactions {} ", transactionDto);

		ResponseEnum responseEnum = getTransactionService().newTransactions(transactionDto);
		HttpStatus status = null;

		switch (responseEnum) {
		case CREATED:
			status = HttpStatus.CREATED;
			break;
		case NO_CONTENT:
			status = HttpStatus.NO_CONTENT;
			break;
			
		case NOT_PARSEABLE:
			status = HttpStatus.UNPROCESSABLE_ENTITY;
			break;
			
		default:
			log.debug("In valid Case");

		}
		return ResponseEntity.status(status).build();

	}

	@DeleteMapping
	public ResponseEntity deleteTransactions() throws Exception {
		log.info(" Request received for delete all transactions");
		getTransactionService().deleteTransactions();
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

	}

}
