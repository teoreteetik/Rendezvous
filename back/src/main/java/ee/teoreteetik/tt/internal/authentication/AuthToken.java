package ee.teoreteetik.tt.internal.authentication;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import ee.teoreteetik.tt.internal.model.User;
import java.sql.Date;
import javax.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@XmlRootElement
public class AuthToken {
  @Getter @Setter private String uuid;
  @Getter @Setter private Date   validUntil;
  @Getter @Setter private User   user;
}
