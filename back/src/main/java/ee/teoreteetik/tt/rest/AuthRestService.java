package ee.teoreteetik.tt.rest;

import ee.teoreteetik.tt.internal.authentication.AuthToken;
import ee.teoreteetik.tt.internal.authentication.Kubjas;
import ee.teoreteetik.tt.internal.authentication.service.AuthService;
import ee.teoreteetik.tt.model.User;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.springframework.beans.factory.annotation.Autowired;

@Path("auth")
public class AuthRestService {

  @Autowired private AuthService googleAuthService;

  // TODO to post
  @GET
  @Path("google/{googleToken}")
  @Produces(MediaType.APPLICATION_JSON)
  public AuthToken authenticate(@PathParam("googleToken") final String googleToken) {
    User user = googleAuthService.getUser(googleToken);
    AuthToken token = Kubjas.login(user);
    return token;
  }

  // TODO POST
  @GET
  @Path("logout/{uuid}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response logout(@PathParam("uuid") final String uuid) {
    Kubjas.logout(uuid);
    return Response.status(201).build();
  }

}
