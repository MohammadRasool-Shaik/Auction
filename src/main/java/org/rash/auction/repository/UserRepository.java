/**
 *
 */
package org.rash.auction.repository;

import org.rash.auction.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author mshai9
 */
public interface UserRepository extends JpaRepository<User, Long> {

    public User findByEmail(String email);
}
