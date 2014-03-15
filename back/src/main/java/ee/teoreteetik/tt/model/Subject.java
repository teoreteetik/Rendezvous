package ee.teoreteetik.tt.model;

import javax.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;

@XmlRootElement
public class Subject {
  @Getter @Setter private Long   id;
  @Getter @Setter private String name;
  @Getter @Setter private String code;
  @Getter @Setter private Long   semesterId;
}
