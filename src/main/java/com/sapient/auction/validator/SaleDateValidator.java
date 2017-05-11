package com.sapient.auction.validator;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.sapient.auction.dto.SaleDTO;
import com.sapient.auction.exception.ValidationException;
import com.sapient.auction.util.AuctionDateUtil;

@Component
public class SaleDateValidator implements ConstraintValidator<ValidSaleDate, SaleDTO> {

	Logger logger = LogManager.getLogger(SaleDateValidator.class);
	@Value("${auction.date.format}")
	private String defaultDateFormat;

	@Override
	public void initialize(ValidSaleDate validSaleDate) {
	}

	@Override
	public boolean isValid(SaleDTO saleDTO, ConstraintValidatorContext context) {
		String endDate = saleDTO.getEndDate();
		String startDate = saleDTO.getStartDate();
		SimpleDateFormat sdf = new SimpleDateFormat(defaultDateFormat);
		try {
			if (AuctionDateUtil.truncatedCompareTo(sdf.parse(endDate), sdf.parse(startDate), 10) > 0)
				return true;
		} catch (ParseException e) {
			logger.error("Date format is incorrect", e);
			throw new ValidationException("Date format is incorrect");
		}

		return false;
	}

}
