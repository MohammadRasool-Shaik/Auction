package com.sapient.auction.service;

import com.sapient.auction.dto.BidDTO;

/**
 * Interface to expose functonalities for bidding
 * 
 * @author team 3
 *
 */
public interface IBidService {

	/**
	 * Saves or updates the sale table with a new bid entity or row
	 * 
	 * @param bid
	 * @return
	 * @throws Exception
	 */
	public BidDTO saveOrUpdate(BidDTO bidDto);

	/**
	 * Find the bid with the highest bid amount for a sale
	 * 
	 * @return double
	 * @throws Exception
	 */
	public BidDTO getMaxBidForSale(Integer saleId);

}
