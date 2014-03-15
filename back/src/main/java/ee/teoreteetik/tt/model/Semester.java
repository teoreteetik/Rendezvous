package ee.teoreteetik.tt.model;

import javax.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;

@XmlRootElement
public class Semester {
  @Getter @Setter private Long    id;
  @Getter @Setter private Integer year;
  @Getter @Setter private Integer semesterNumber;
  @Getter @Setter private String  text;
}
