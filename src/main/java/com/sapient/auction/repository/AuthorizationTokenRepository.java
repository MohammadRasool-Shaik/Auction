/**
 * 
 */
package com.sapient.auction.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sapient.auction.model.AuthorizationToken;

/**
 * @author Admin
 *
 */
public interface AuthorizationTokenRepository extends JpaRepository<AuthorizationToken, Long> {

	@Query("select t from AuthorizationToken t where token = ?")
	AuthorizationToken findByToken(String token);
}
