package ee.teoreteetik.tt.rest;

import ee.teoreteetik.tt.service.UserService;
import javax.ws.rs.Path;
import org.springframework.beans.factory.annotation.Autowired;

//NOT IMPLEMENTED
@Path("/users")
public class UserRestService {

  @Autowired private UserService userService;

}
