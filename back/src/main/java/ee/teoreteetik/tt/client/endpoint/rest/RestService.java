package ee.teoreteetik.tt.client.endpoint.rest;

import ee.teoreteetik.tt.internal.authentication.Kubjas;
import ee.teoreteetik.tt.internal.model.User;
import java.util.List;
import javax.ws.rs.core.HttpHeaders;

public abstract class RestService {

  private static final String AUTH_HEADER = "Authorization";

  public User getUser(HttpHeaders headers) {
    List<String> uuids = headers.getRequestHeader(AUTH_HEADER);
    if (uuids == null || uuids.size() != 1) {
      return null;
    }
    User user = Kubjas.getUser(uuids.get(0));
    return user;
  }
}
