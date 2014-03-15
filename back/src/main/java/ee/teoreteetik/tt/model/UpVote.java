package ee.teoreteetik.tt.model;

import javax.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;

@XmlRootElement
public class UpVote {
  @Getter @Setter private Long id;
  @Getter @Setter private Long userId;
  @Getter @Setter private Long publicationId;
}
