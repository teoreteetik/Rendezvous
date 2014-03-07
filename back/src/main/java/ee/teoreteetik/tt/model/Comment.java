package ee.teoreteetik.tt.model;

import java.sql.Date;

public class Comment {
  private Long id;
  private String textString;
  private boolean anonymous;
  private Long topicId;
  private Long userId;
  private Date datePosted;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getTextString() {
    return textString;
  }

  public void setTextString(String textString) {
    this.textString = textString;
  }

  public boolean isAnonymous() {
    return anonymous;
  }

  public void setAnonymous(boolean anonymous) {
    this.anonymous = anonymous;
  }

  public Long getTopicId() {
    return topicId;
  }

  public void setTopicId(Long topicId) {
    this.topicId = topicId;
  }

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public Date getDatePosted() {
    return datePosted;
  }

  public void setDatePosted(Date datePosted) {
    this.datePosted = datePosted;
  }

}
