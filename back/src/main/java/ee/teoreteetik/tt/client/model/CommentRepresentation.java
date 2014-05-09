package ee.teoreteetik.tt.client.model;


import java.util.Date;

public class CommentRepresentation {

  public static class CommentForDisplay {
    public Long id;
    public String textHtml;
    public String username;
    public Date datePosted;
    public int score;
  }

  public static class CreateComment {
    public Long parentPublicationId;
    public String textHtml;
    public String textPlain;
    public boolean anonymous;
  }

}
