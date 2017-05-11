package com.sapient.auction.service.impl;

import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sapient.auction.model.Sale;
import com.sapient.auction.repository.SaleRepository;
import com.sapient.auction.service.ISaleService;

/**
 * Implementation for the sale details functionalities.
 * 
 * @author jyengk
 *
 */
@Service
public class SaleService implements ISaleService {

	private static Logger logger = Logger.getLogger(SaleService.class.getName());

	@Autowired
	private SaleRepository saleRepository;

	@Override
	public Sale saveOrUpdate(Sale sale) throws Exception {

		// TODO move this to an assembler class if possible
		sale.setCreatedDate(new Date());
		return saleRepository.save(sale);

	}

	@Override
	public List<Sale> getActiveSales() throws Exception {
		// return saleRepository.findByActive(true);
		return null;
	}

	@Override
	public Sale findBySaleId(Integer saleId) throws Exception {
		return saleRepository.findBySaleId(saleId);
	}

	@Override
	public List<Sale> fetchAllSales() {
		return saleRepository.findAll();
	}

}
