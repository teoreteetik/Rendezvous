package ee.teoreteetik.tt.client.endpoint.rest;

import ee.teoreteetik.tt.client.exception.NotAuthorizedException;
import ee.teoreteetik.tt.internal.service.UserService;
import ee.teoreteetik.tt.internal.model.User;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

@Path("/users")
public class UserRestService extends RestService {

  private static final Logger    logger = LogManager.getLogger(UserRestService.class);

  @Autowired private UserService userService;

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public Response updateUser(@Context HttpHeaders headers, final User user) {
    User authenticatedUser = getUser(headers);

    if (authenticatedUser != null && authenticatedUser.getId().equals(user.getId())) {
      authenticatedUser.setUsername(user.getUsername());
      userService.update(authenticatedUser);
      return Response.status(201).entity(authenticatedUser).build();
    } else {
      throw new NotAuthorizedException();
    }

  }
}
