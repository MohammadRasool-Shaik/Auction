package com.sapient.auction.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.stereotype.Component;

@Component
public class AuctionDateUtil extends DateUtils {

	public Date convertToDefaultDateFormat(String defaultDateFormat, String date) throws ParseException {
		SimpleDateFormat sdfDestination = new SimpleDateFormat(defaultDateFormat);
		Date parsedDate = sdfDestination.parse(date + " 00:00:00");
		return sdfDestination.parse(sdfDestination.format(parsedDate));
	}

	public String convertDateToString(String dateFormat, Date date) {
		SimpleDateFormat sdfDestination = new SimpleDateFormat(dateFormat);
		return sdfDestination.format(date);
	}
}
