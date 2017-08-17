/**
 *
 */
package org.rash.auction.repository;

import org.rash.auction.enumerator.SaleStatus;
import org.rash.auction.model.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * A database repository for performing database functionalities.
 *
 * @author mshai9
 */
@Transactional
public interface SaleRepository extends JpaRepository<Sale, Integer> {

    Sale findBySaleId(Integer saleId);

    List<Sale> findBySaleStatus(SaleStatus saleStatus);

}
