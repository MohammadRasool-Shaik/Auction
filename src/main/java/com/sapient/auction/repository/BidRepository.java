package com.sapient.auction.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.sapient.auction.model.Bid;

@Transactional
public interface BidRepository extends JpaRepository<Bid, Integer> {

	@Modifying
	@Query("select b from Bid b where b.amount in" + "(select max(b.amount) from Bid b where b.id = :saleId) and b.id = :saleId order by b.time asc")
	public List<Bid> findFirstMaxBidForSale(Integer saleId, PageRequest pageRequest);
}
