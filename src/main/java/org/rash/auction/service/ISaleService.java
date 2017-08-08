package org.rash.auction.service;

import java.util.List;

import org.rash.auction.dto.SaleDTO;

/**
 * Exposes functionalities related to sales
 * 
 * @author Team3
 *
 */
public interface ISaleService {

	/**
	 * Saves or updates the sale table with a new sale entity or row
	 * 
	 * @param sale
	 * @param email TODO
	 * @return Sale
	 * @throws Exception
	 */
	public SaleDTO saveOrUpdate(SaleDTO sale, String email);

	/**
	 * Find all the active sales
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<SaleDTO> getActiveSales();

	/**
	 * Find the sale details for a desired sale id.
	 * 
	 * @param sale
	 * @return
	 * @throws Exception
	 */
	public SaleDTO findBySaleId(Integer sale);
}