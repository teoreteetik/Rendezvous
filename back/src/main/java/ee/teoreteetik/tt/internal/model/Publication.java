package ee.teoreteetik.tt.internal.model;

import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;

@XmlRootElement
public abstract class Publication {
  @Getter @Setter private Long    id;
  @Getter @Setter private String  textHtml;
  @Getter @Setter private String  textPlain;
  @Getter @Setter private boolean anonymous;
  @Getter @Setter private Long    userId;
  @Getter @Setter private Date    datePosted;

}
