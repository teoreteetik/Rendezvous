package ee.teoreteetik.tt.internal.model;

import javax.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;

@XmlRootElement
public class Topic extends Publication {
  @Getter @Setter private String title;
  @Getter @Setter private Long   subjectId;
}
