/**
 * 
 */
package org.rash.auction.service;

import java.util.List;

import org.rash.auction.dto.AuthenticatedUserToken;
import org.rash.auction.dto.LoginDTO;
import org.rash.auction.dto.ResponseStatus;
import org.rash.auction.model.User;

/**
 * @author mshai9
 *
 */
public interface IUserService {

	public ResponseStatus creatUser(User user);

	public AuthenticatedUserToken login(LoginDTO request);

	public List<User> fetchAllUsers();
}
