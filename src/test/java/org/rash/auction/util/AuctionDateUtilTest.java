package org.rash.auction.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import static org.junit.Assert.assertEquals;
import org.rash.auction.ApplicationBootStart;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ApplicationBootStart.class)
public class AuctionDateUtilTest {
	
	@Autowired
	private AuctionDateUtil dateUtil;
	
	@Value("${auction.datetime.format}")
	private String defaultDateTimeFormat;
	
	@Test
	public void testConvertToDefaultDateFormat() throws ParseException{
		Date example = dateUtil.convertToDefaultDateFormat(defaultDateTimeFormat, "2014-09-02");
		SimpleDateFormat sdfDestination = new SimpleDateFormat(defaultDateTimeFormat);
		assertEquals(sdfDestination.format(example), "2014-09-02 00:00:00");
	}
}
