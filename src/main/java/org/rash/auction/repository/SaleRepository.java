/**
 * 
 */
package org.rash.auction.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import org.rash.auction.enumerator.SaleStatus;
import org.rash.auction.model.Sale;

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
