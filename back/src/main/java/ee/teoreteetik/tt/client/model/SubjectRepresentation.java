package ee.teoreteetik.tt.client.model;

public class SubjectRepresentation {

  public static class SubjectForDisplay {
    public Long id;
    public String name;
    public String code;
    public Long semesterId;
    public int topicCount;
  }

  public static class CreateSubject {
    public String name;
    public String code;
    public Long semesterId;
  }

}
