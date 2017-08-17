/**
 *
 */
package org.rash.auction.service.impl;

import org.rash.auction.dto.AuthenticatedUserToken;
import org.rash.auction.dto.LoginDTO;
import org.rash.auction.dto.ResponseStatus;
import org.rash.auction.exception.AuthenticationException;
import org.rash.auction.model.User;
import org.rash.auction.repository.UserRepository;
import org.rash.auction.service.IUserService;
import org.rash.auction.util.JWTokenUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * @author mshai9
 */
@Service("userService")
public class UserService implements IUserService {
    private static Logger logger = LoggerFactory.getLogger(UserService.class.getName());

    @Autowired
    private UserRepository userRepository;

    @Transactional
    @Override
    public ResponseStatus creatUser(User user) {
        User searchedForUser = userRepository.findByEmail(user.getEmail().toLowerCase().trim());
        if (searchedForUser != null) {
            logger.error(user.getEmail() + " email_id is taken. Try another..!");
            throw new AuthenticationException(user.getEmail() + " email_id is taken. Try another");
        }
        user.setPassword(user.hashPassword(user.getPassword()));
        User savedUser = userRepository.save(user);
        if (savedUser != null) {
            return new ResponseStatus(Response.Status.OK.getStatusCode(), "User successfully created");
        } else {
            logger.error("Got Some error while saving the user..!");
            return new ResponseStatus(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), "Got Some error while creating the user");
        }

    }

    /**
     * {@inheritDoc}
     * <p>
     * Login supports authentication against an email attribute. If a User is retrieved that matches, the password in the request is hashed and compared to the persisted password
     * for the User account.
     */
    @Override
    @Transactional
    public AuthenticatedUserToken login(LoginDTO request) {
        User user = userRepository.findByEmail(request.getEmail());
        if (user == null) {
            logger.error(request.getEmail() + " not exists in the database");
            throw new AuthenticationException(Response.Status.FORBIDDEN.getStatusCode(), "Access Denied for this functionality !!!");
        }
        String hashedPassword = user.hashPassword(request.getPassword());
        if (hashedPassword.equals(user.getPassword())) {
            // Issue a token for the user
            AuthenticatedUserToken authenticatedUserToken = new AuthenticatedUserToken(user.getEmail(), "Bearer " + JWTokenUtility.buildJWT(user));
            authenticatedUserToken.setStatus(new ResponseStatus(Response.Status.OK.getStatusCode(), user.getDisplayName() + " Successfully loggedin"));
            return authenticatedUserToken;
        } else {
            logger.error(request.getEmail() + " password is not correct");
            throw new AuthenticationException();
        }
    }

    @Override
    public List<User> fetchAllUsers() {
        return userRepository.findAll();
    }

    public User findUser(Long id) {
        return userRepository.findOne(id);
    }

}
