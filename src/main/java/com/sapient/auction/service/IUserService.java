/**
 * 
 */
package com.sapient.auction.service;

import java.util.List;

import com.sapient.auction.dto.AuthenticatedUserToken;
import com.sapient.auction.dto.LoginDTO;
import com.sapient.auction.dto.ResponseStatus;
import com.sapient.auction.model.User;

/**
 * @author mshai9
 *
 */
public interface IUserService {

	public ResponseStatus creatUser(User user);

	public AuthenticatedUserToken login(LoginDTO request);

	public List<User> fetchAllUsers();
}
