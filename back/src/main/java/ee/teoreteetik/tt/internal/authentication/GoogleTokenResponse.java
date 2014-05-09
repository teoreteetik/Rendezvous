package ee.teoreteetik.tt.internal.authentication;

import lombok.Getter;
import lombok.Setter;

public class GoogleTokenResponse {
  @Getter @Setter String issued_to;
  @Getter @Setter String audience;
  @Getter @Setter String user_id;
  @Getter @Setter String scope;
  @Getter @Setter String expires_in;
  @Getter @Setter String email;
  @Getter @Setter String verified_email;
  @Getter @Setter String access_type;
}
