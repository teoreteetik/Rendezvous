package ee.teoreteetik.tt.rest;

import ee.teoreteetik.tt.internal.authentication.Kubjas;
import ee.teoreteetik.tt.internal.service.UserService;
import ee.teoreteetik.tt.model.User;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.springframework.beans.factory.annotation.Autowired;

//NOT IMPLEMENTED
@Path("/users")
public class UserRestService {

  @Autowired private UserService userService;

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public Response updateUser(@Context HttpHeaders headers, final User user) {

    List<String> uuids = headers.getRequestHeader("Authorization");
    if (uuids == null || Kubjas.getUser(uuids.get(0)) == null) {
      return Response.status(401).build();
    }
    String uuid = uuids.get(0);
    User authenticatedUser = Kubjas.getUser(uuid);
    if (authenticatedUser != null && authenticatedUser.getId().equals(user.getId())) {
      authenticatedUser.setUsername(user.getUsername());
      userService.update(authenticatedUser);
      return Response.status(201).entity(authenticatedUser).build();
    } else {
      return Response.status(401).build();
    }

  }
}
