package org.rash.auction.resource;

import org.rash.auction.dto.AuthenticatedUserToken;
import org.rash.auction.dto.LoginDTO;
import org.rash.auction.dto.ResponseStatus;
import org.rash.auction.model.User;
import org.rash.auction.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.security.PermitAll;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.net.URI;
import java.util.Calendar;

/**
 * @author mshai9
 */

@Path("/users")
@Produces({MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_JSON})
public class UserResource {

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
    public ResponseStatus registerUser(User user) {
        return userService.creatUser(user);
    }

    @PermitAll
    @Path("login")
    @POST
    public AuthenticatedUserToken login(LoginDTO request, @Context SecurityContext securityContext) {
        AuthenticatedUserToken token = userService.login(request);
        return token;
    }

    private Response getLoginResponse(AuthenticatedUserToken token, SecurityContext securityContext) {
        URI location = UriBuilder.fromPath(uriInfo.getBaseUri() + "users/" + token.getEmail()).build();
        return Response.ok().entity(token).contentLocation(location).build();
    }

}
