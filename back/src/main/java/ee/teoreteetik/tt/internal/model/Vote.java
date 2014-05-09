package ee.teoreteetik.tt.internal.model;

import javax.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;

@XmlRootElement
public class Vote {
  @Getter @Setter private Long id;
  @Getter @Setter private int value;
  @Getter @Setter private Long userId;
  @Getter @Setter private Long publicationId;
}
