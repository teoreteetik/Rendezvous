package ee.teoreteetik.tt.internal.model;

import ee.teoreteetik.tt.internal.authentication.Privilege;
import java.util.Set;
import javax.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;

@XmlRootElement
public class User {

  @Getter @Setter private Long           id;
  @Getter @Setter private String         username;
  @Getter @Setter private String         email;
  @Getter @Setter private Set<Privilege> privileges;

  public boolean hasAllPrivileges(Privilege... mandatoryPrivileges) {
    for (Privilege mandatoryPrivilege : mandatoryPrivileges) {
      if (!privileges.contains(mandatoryPrivilege)) {
        return false;
      }
    }
    return true;
  }

}
