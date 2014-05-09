package ee.teoreteetik.tt.client.endpoint.rest.provider;

import ee.teoreteetik.tt.client.exception.NotAuthorizedException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class NotAuthorizedExceptionMapper implements ExceptionMapper<NotAuthorizedException>{
  @Override
  public Response toResponse(NotAuthorizedException exception) {
    return Response.status(Response.Status.UNAUTHORIZED).build();
  }
}
