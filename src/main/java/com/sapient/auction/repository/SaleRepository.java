/**
 * 
 */
package com.sapient.auction.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sapient.auction.enumerator.SaleStatus;
import com.sapient.auction.model.Sale;

/**
 * A database repository for performing database functionalities.
 * 
 * @author mshai9
 *
 */
@Transactional
public interface SaleRepository extends JpaRepository<Sale, Integer> {

	Sale findBySaleId(Integer saleId);

	List<Sale> findBySaleStatus(SaleStatus saleStatus);

}
