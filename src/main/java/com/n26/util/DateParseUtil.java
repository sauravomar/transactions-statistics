package com.n26.util;

import java.text.ParseException;
import java.time.Instant;
import java.util.Date;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class DateParseUtil {

	public Date parseDateFromString(String date) throws ParseException {
		log.debug(" Parsing date {}  ", date);
		return Date.from(Instant.parse(date));
	}
}
