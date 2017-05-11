package com.sapient.auction.resource;

import java.net.URI;
import java.util.Calendar;
import java.util.logging.Logger;

import javax.annotation.security.PermitAll;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import org.springframework.beans.factory.annotation.Autowired;

import com.sapient.auction.dto.AuthenticatedUserToken;
import com.sapient.auction.dto.LoginRequest;
import com.sapient.auction.model.User;
import com.sapient.auction.service.IUserService;

/**
 * 
 * @author mshai9
 *
 */

@Path("/user")
@Produces({ MediaType.APPLICATION_JSON })
@Consumes({ MediaType.APPLICATION_JSON })
public class UserResource {

	private static Logger logger = Logger.getLogger(UserResource.class.getName());

	@Autowired
	public IUserService userService;

	@Context
	protected UriInfo uriInfo;

	@GET
	public Response ping() {
		return Response.ok().entity("Running version " + Calendar.getInstance().getTime()).build();
	}

	@PermitAll
	@POST
	public AuthenticatedUserToken registerUser(User user) throws Exception {
		AuthenticatedUserToken creatUser = userService.creatUser(user);
		return creatUser;
	}

	@PermitAll
	@Path("login")
	@POST
	public Response login(LoginRequest request) {
		AuthenticatedUserToken token = userService.login(request);
		return getLoginResponse(token);
	}

	private Response getLoginResponse(AuthenticatedUserToken token) {
		URI location = UriBuilder.fromPath(uriInfo.getBaseUri() + "user/" + token.getUserId()).build();
		return Response.ok().entity(token).contentLocation(location).build();
	}
}
