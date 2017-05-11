package com.sapient.auction.service;

import java.util.List;

import com.sapient.auction.model.Sale;

/**
 * Exposes functionalities related to sales
 * @author jyengk
 *
 */
public interface ISaleService {
	
	public Sale saveOrUpdate(Sale sale) throws Exception;
	public List<Sale> getActiveSales() throws Exception;
	public Sale findBySaleId(Integer sale) throws Exception;
	public List<Sale> fetchAllSales();
}