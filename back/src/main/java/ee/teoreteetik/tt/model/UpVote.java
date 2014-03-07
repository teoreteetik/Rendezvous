package ee.teoreteetik.tt.model;

public class UpVote {
  private Long idLong;
  private Long userId;
  private Long topicId;
  private Long commentIdLong;

  public Long getIdLong() {
    return idLong;
  }

  public void setIdLong(Long idLong) {
    this.idLong = idLong;
  }

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public Long getTopicId() {
    return topicId;
  }

  public void setTopicId(Long topicId) {
    this.topicId = topicId;
  }

  public Long getCommentIdLong() {
    return commentIdLong;
  }

  public void setCommentIdLong(Long commentIdLong) {
    this.commentIdLong = commentIdLong;
  }

}
