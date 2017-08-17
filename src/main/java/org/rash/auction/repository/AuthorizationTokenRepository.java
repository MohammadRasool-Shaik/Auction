/**
 *
 */
package org.rash.auction.repository;

import org.rash.auction.model.AuthorizationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * @author mshai9
 */
public interface AuthorizationTokenRepository extends JpaRepository<AuthorizationToken, Long> {

    @Query("select t from AuthorizationToken t where token = ?")
    AuthorizationToken findByToken(String token);
}
