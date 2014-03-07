package ee.teoreteetik.tt.rest;

import ee.teoreteetik.tt.model.User;
import ee.teoreteetik.tt.service.UserService;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.springframework.beans.factory.annotation.Autowired;

//NOT IMPLEMENTED
@Path("/users")
public class UserRestService {

  @Autowired
  private UserService userService;

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public User getDefaultUserInJSON() {
    User user = userService.getById(1L);
    return user;
  }
}
