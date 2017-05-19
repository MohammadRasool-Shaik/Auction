package com.sapient.auction.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sapient.auction.dto.SaleDTO;
import com.sapient.auction.enumerator.SaleStatus;
import com.sapient.auction.exception.NotFoundException;
import com.sapient.auction.model.Sale;
import com.sapient.auction.model.User;
import com.sapient.auction.repository.SaleRepository;
import com.sapient.auction.repository.UserRepository;
import com.sapient.auction.service.ISaleService;
import com.sapient.auction.util.EntityModelAssembler;

/**
 * Implementation for the sale details functionalities.
 * 
 * @author team 3
 *
 */
@Service
public class SaleService implements ISaleService {

	private static Logger logger = LoggerFactory.getLogger(SaleService.class.getName());

	@Autowired
	private SaleRepository saleRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private EntityModelAssembler assembler;

	/**
	 * Create a sale for a particular user
	 * @param SaleDTO
	 * 
	 * @return SaleDTO
	 * @throws NotFoundException,
	 *             ValidationException
	 */
	@Override
	public SaleDTO saveOrUpdate(SaleDTO saleDto, String email) {
		logger.info("Saving or updating sale with details :" + saleDto);
		// Get the buyer details by the email ID
		User user = userRepository.findByEmail(email);
		if (user == null) {
			throw new NotFoundException("The buyer does not exist");
		}
		Sale sale = saleRepository.save(assembler.buildSaleEntity(saleDto, user));
		return assembler.convertSaleEntityToDto(sale);
	}

	/**
	 * Returns a list of sales which are active. No exception is thrown in case no data is found for any active sales. An empty list will be returned to the client. An empty array
	 * will be returned in case the list is empty
	 * 
	 * @return List<SaleDTO>
	 * 
	 */
	@Override
	public List<SaleDTO> getActiveSales() {

		List<Sale> sales = saleRepository.findBySaleStatus(SaleStatus.A);
		// sales.stream().map(a -> assembler::convertSaleDtoToEntity);
		List<SaleDTO> dtos = new ArrayList<>();
		for (Sale entity : sales) {
			dtos.add(assembler.convertSaleEntityToDto(entity));
		}
		return dtos;
	}

	/**
	 * Find a sale by saleId
	 * 
	 * @param saleId
	 * @return
	 * @throws Exception
	 */
	@Override
	public SaleDTO findBySaleId(Integer saleId) {
		Sale sale = saleRepository.findBySaleId(saleId);
		if (sale == null) {
			throw new NotFoundException("No sale details found for sale id " + saleId);
		}
		return assembler.convertSaleEntityToDto(sale);
	}

}
