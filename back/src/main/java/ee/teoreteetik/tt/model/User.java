package ee.teoreteetik.tt.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class User {

  private String name;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

}
