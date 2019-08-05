package com.n26.service.impl;

import java.time.Instant;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.n26.dto.TransactionDto;
import com.n26.model.Transactions;
import com.n26.service.ValidationService;
import com.n26.util.DateParseUtil;

import lombok.extern.slf4j.Slf4j;

@Service("validationSvc")
@Slf4j
@lombok.Getter
@lombok.Setter
public class ValidationServiceImpl implements ValidationService {

	DateParseUtil dateUtil;

	@Autowired
	public ValidationServiceImpl(DateParseUtil util) {
		this.dateUtil = util;
	}

	public boolean validateTime(Transactions dto) {

		log.info(" validating transaction dto {} ", dto);

		boolean isValid = true;
		Instant prevTime = Instant.now().minusSeconds(60);

		if (dto.getTimestamp().compareTo(Date.from(prevTime)) <= 0
				|| dto.getTimestamp().compareTo(Date.from(Instant.now())) > 0) {
			return false;
		}
		return isValid;

	}

	public boolean validateFutureTime(Transactions dto) {
		log.info(" validating transaction dto {} ", dto);
		boolean isValid = true;

		if (dto.getTimestamp().compareTo(Date.from(Instant.now())) > 0) {
			return false;
		}
		return isValid;

	}

	public boolean validateDateFormat(TransactionDto dto) {

		log.info(" validating transaction dto {} ", dto);
		boolean isValid = false;

		try {
			getDateUtil().parseDateFromString(dto.getTimestamp());
		} catch (Exception e) {
			return isValid;
		}

		return true;
	}

	public boolean validateAmount(String amount) {
		log.info(" validating transaction amount {} ", amount);
		boolean isValid = false;

		try {
			Double.parseDouble(amount);
		} catch (Exception e) {
			return isValid;
		}

		return true;
	}

}
