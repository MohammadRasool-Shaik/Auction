/**
 * 
 */
package com.sapient.auction.service;

import com.sapient.auction.dto.AuthenticatedUserToken;
import com.sapient.auction.dto.LoginRequest;
import com.sapient.auction.model.AuthorizationToken;
import com.sapient.auction.model.User;

/**
 * @author Admin
 *
 */
public interface IUserService {

	public AuthenticatedUserToken creatUser(User user);

	public AuthorizationToken createAuthorizationToken(User user);

	public AuthenticatedUserToken login(LoginRequest request);

}
