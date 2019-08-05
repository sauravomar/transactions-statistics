package com.n26.util;

import java.text.ParseException;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import junit.framework.Assert;

@SuppressWarnings({ "deprecation" })
public class DateParseUtilTest {

	@Spy
	private DateParseUtil dateParseUtil;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void parseDateFromString() throws ParseException {
		Date result = dateParseUtil.parseDateFromString("2018-07-17T09:59:51.312Z");
		Assert.assertNotNull(result);

	}
}
