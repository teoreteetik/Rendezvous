package ee.teoreteetik.tt.client.endpoint.rest;

import ee.teoreteetik.tt.internal.authentication.AuthToken;
import ee.teoreteetik.tt.internal.authentication.Kubjas;
import ee.teoreteetik.tt.internal.authentication.service.AuthService;
import ee.teoreteetik.tt.internal.model.User;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

@Path("/auth")
public class AuthRestService extends RestService {

  private final Logger           logger = LogManager.getLogger(AuthRestService.class);
  @Autowired private AuthService googleAuthService;

  // TODO POST
  @GET
  @Path("google/{googleToken}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response authenticate(@PathParam("googleToken") final String googleToken) {
    User user = googleAuthService.getUser(googleToken);
    if (user != null) {
      AuthToken token = Kubjas.login(user);
      return Response.status(201).entity(token).build();
    } else {
      return Response.status(Response.Status.UNAUTHORIZED).build();
    }
  }

  // TODO POST
  @GET
  @Path("logout/{uuid}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response logout(@PathParam("uuid") final String uuid) {
    Kubjas.logout(uuid);
    return Response.status(201).build();
  }

  @GET
  @Path("google/getUser/{uuid}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response getToken(@PathParam("uuid") final String uuid) {
    return Response.status(201).entity(Kubjas.getToken(uuid)).build();

  }

}
