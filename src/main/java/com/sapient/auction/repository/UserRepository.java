/**
 * 
 */
package com.sapient.auction.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sapient.auction.model.User;

/**
 * @author mshai9
 *
 */
public interface UserRepository extends JpaRepository<User, Long> {

	public User findByEmail(String email);
}
