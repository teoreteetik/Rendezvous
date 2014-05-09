package ee.teoreteetik.tt.client.model;

import java.util.Date;
import java.util.List;
import ee.teoreteetik.tt.client.model.CommentRepresentation.CommentForDisplay;


public class TopicRepresentation {

  public static class TopicForDisplay {
    public Long id;
    public String title;
    public String textHtml;
    public String username;
    public Date datePosted;
    public List<CommentForDisplay> comments;
  }

  public static class TopicForListDisplay {
    public Long id;
    public String title;
    public String textHtml;
    public String username;
    public Date datePosted;
    public int commentCount;
  }

  public static class CreateTopic {
    public String title;
    public String textHtml;
    public String textPlain;
    public boolean anonymous;
    public Long subjectId;
  }

}
