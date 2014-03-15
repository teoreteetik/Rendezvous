package ee.teoreteetik.tt.model;

import javax.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;

@XmlRootElement
public class User {

  @Getter @Setter private Long   id;
  @Getter @Setter private String username;
  @Getter @Setter private String name;

}
