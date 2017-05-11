/**
 * 
 */
package com.sapient.auction.service.impl;

import java.util.logging.Logger;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sapient.auction.dto.AuthenticatedUserToken;
import com.sapient.auction.dto.LoginRequest;
import com.sapient.auction.exception.AuthenticationException;
import com.sapient.auction.model.AuthorizationToken;
import com.sapient.auction.model.User;
import com.sapient.auction.repository.UserRepository;
import com.sapient.auction.service.IUserService;

/**
 * @author mshai9
 *
 */
@Service("userService")
public class UserService implements IUserService {
	private static Logger logger = Logger.getLogger(UserService.class.getName());

	@Autowired
	private UserRepository userRepository;

	@Transactional
	@Override
	public AuthenticatedUserToken creatUser(User user) {
		User searchedForUser = userRepository.findByEmail(user.getEmail().toLowerCase().trim());
		if (searchedForUser != null) {
			throw new AuthenticationException("User already exists with same email-id");
		}
		user.setEmail(user.getEmail().toLowerCase());
		user.setPassword(user.hashPassword(user.getPassword()));
		AuthenticatedUserToken authenticatedUserToken = new AuthenticatedUserToken(user.getEmail(),
				createAuthorizationToken(user).getToken());
		return authenticatedUserToken;
	}

	@Override
	public AuthorizationToken createAuthorizationToken(User user) {
		if (user.getAuthorizationToken() == null || user.getAuthorizationToken().hasExpired()) {
			user.setAuthorizationToken(new AuthorizationToken(user));
			userRepository.save(user);
		}
		return user.getAuthorizationToken();
	}

	/**
	 * {@inheritDoc}
	 *
	 * Login supports authentication against an email attribute. If a User is
	 * retrieved that matches, the password in the request is hashed and
	 * compared to the persisted password for the User account.
	 */
	@Override
	@Transactional
	public AuthenticatedUserToken login(LoginRequest request) {
		User user = null;
		user = userRepository.findByEmail(request.getUsername());
		if (user == null) {
			throw new AuthenticationException("Authentication Error. The username or password were incorrect");
		}
		String hashedPassword = null;
		try {
			hashedPassword = user.hashPassword(request.getPassword());
		} catch (Exception e) {
			throw new AuthenticationException("Authentication Error. The username or password were incorrect");
		}
		if (hashedPassword.equals(user.getPassword())) {
			return new AuthenticatedUserToken(user.getEmail(), createAuthorizationToken(user).getToken());
		} else {
			throw new AuthenticationException("Authentication Error. The username or password were incorrect");
		}
	}

}
