package ee.teoreteetik.tt.internal.model;

import javax.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;

@XmlRootElement
public class Comment extends Publication {
  @Getter @Setter private Long parentPublicationId;
}
