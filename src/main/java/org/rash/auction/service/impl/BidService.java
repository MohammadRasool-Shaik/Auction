package org.rash.auction.service.impl;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.apache.commons.collections4.MapUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.rash.auction.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.rash.auction.dto.BidDTO;
import org.rash.auction.exception.NotFoundException;
import org.rash.auction.model.Bid;
import org.rash.auction.model.Sale;
import org.rash.auction.model.User;
import org.rash.auction.repository.BidRepository;
import org.rash.auction.repository.SaleRepository;
import org.rash.auction.service.IBidService;
import org.rash.auction.util.EntityModelAssembler;

/**
 * Implementation of the IBIdService which will cater to creating bids for a sale and to retrieve maximum bids for a particular sale
 * 
 * @author Team 3
 *
 */
@Service
public class BidService implements IBidService {

	Logger logger = LogManager.getLogger(BidService.class);

	// TODO move to a HazelCast hashmap for achieving in-memory grid access across clusters.
	// Cache for holding the maximum bid for a particular sale ID
	public final ConcurrentMap<Integer, BidDTO> HIGHEST_BID_CACHE = new ConcurrentHashMap<>();

	@Autowired
	public BidRepository bidRepository;

	@Autowired
	public UserRepository userRepository;

	@Autowired
	public SaleRepository saleRepository;

	@Autowired
	public EntityModelAssembler assembler;

	/**
	 * TODO - Asynch the database update with the cache update. Preferably use HazelCast for cluster sharing of cache. Save or update a Bid placed for a sale in the Bid table and
	 * update the cache. The cache will store the key-value pair in the ConcurrentHashMap in the following manner key - 12345 (Sale ID) value - BidDTO
	 * 
	 * @return BidDTO
	 */
	@Override
	public BidDTO saveOrUpdate(BidDTO bidDto) {

		logger.debug(">>> Updating the bid details in DB and cache");
		Sale sale = saleRepository.findBySaleId(bidDto.getSaleId());
		if (sale == null) {
			throw new NotFoundException("The sale or the user does not exist");
		}
		User user = userRepository.findByEmail(bidDto.getBuyerEmail());
		Bid savedBid = bidRepository.save(assembler.buildBidEntity(bidDto, sale, user));
		logger.debug(">>>Saved bid is :" + savedBid);
		Integer saleId = savedBid.getSale().getSaleId();
		// Add the new amount to the cache.
		if (MapUtils.isEmpty(HIGHEST_BID_CACHE) || (bidDto.getAmount().compareTo(HIGHEST_BID_CACHE.get(saleId).getAmount()) > 0)) {
			HIGHEST_BID_CACHE.put(saleId, bidDto);
		}
		bidDto.setId(savedBid.getId());
		logger.debug(">>>Updated the new bid in DB");
		return bidDto;
	}

	/**
	 * Fetch the bid details of the highest bid for a particular sale.
	 * 
	 * @return Bid the highest bid details
	 * @param Integer
	 *            sale id
	 */
	@Override
	public BidDTO getMaxBidForSale(Integer saleId) {
		logger.debug(">>> Getting maximum bid for the sale id :" + saleId);
		BidDTO bidDto = HIGHEST_BID_CACHE.get(saleId);
		if (bidDto == null) {
			logger.error("No bid record found for the give sale id " + saleId);
			throw new NotFoundException("No bids found for the sale id " + saleId);
		}
		logger.debug(">>> The highes bid is " + bidDto);
		return bidDto;
	}

}
