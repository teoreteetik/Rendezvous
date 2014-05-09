package ee.teoreteetik.tt.internal.model;

import lombok.Getter;
import lombok.Setter;

public class Subject {
  @Getter @Setter private Long   id;
  @Getter @Setter private String name;
  @Getter @Setter private String code;
  @Getter @Setter private Long userId;
  @Getter @Setter private Long   semesterId;
}
