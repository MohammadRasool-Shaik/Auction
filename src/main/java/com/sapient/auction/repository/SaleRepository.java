/**
 * 
 */
package com.sapient.auction.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.sapient.auction.model.Sale;

/**
 * @author mshai9
 *
 */
public interface SaleRepository extends CrudRepository<Sale, Integer> {

	Sale findBySaleId(Integer saleId);

	@SuppressWarnings("unchecked")
	Sale save(Sale sale);

//	List<Sale> findByActive(boolean isActive);
	
	List<Sale> findAll();
	
}
