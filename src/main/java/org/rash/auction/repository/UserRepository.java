/**
 * 
 */
package org.rash.auction.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.rash.auction.model.User;

/**
 * @author mshai9
 *
 */
public interface UserRepository extends JpaRepository<User, Long> {

	public User findByEmail(String email);
}
