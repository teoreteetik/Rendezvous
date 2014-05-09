package ee.teoreteetik.tt.client.service.impl;

import ee.teoreteetik.tt.client.exception.NotAuthorizedException;
import ee.teoreteetik.tt.client.model.CommentRepresentation.CreateComment;
import ee.teoreteetik.tt.client.model.CommentRepresentation.CommentForDisplay;
import ee.teoreteetik.tt.client.service.CommentClientService;
import ee.teoreteetik.tt.internal.authentication.Privilege;
import ee.teoreteetik.tt.internal.dao.TopicDAO;
import ee.teoreteetik.tt.internal.model.Comment;
import ee.teoreteetik.tt.internal.model.User;
import ee.teoreteetik.tt.internal.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service("commentClientService")
public class CommentClientServiceImpl extends BaseClientServiceImpl implements CommentClientService{

  @Autowired
  private CommentService commentService;

  @Override
  public List<CommentForDisplay> getCommentsForTopic(Long topicId) {
    String sql = ""
            + "SELECT c.id, "
            + "       c.text_html, "
            + "       c.date_posted, "
            + "       CASE "
            + "         WHEN c.anonymous = TRUE THEN 'Peeter Paan' "
            + "         ELSE c.username "
            + "       END AS username, "
            + "       c.score "
            + "FROM   v_comment c "
            + "WHERE  c.parent_publication_id = ?";
    List<CommentForDisplay> result = getJdbcTemplate().query(sql, new Object[]{topicId}, new ParameterizedRowMapper<CommentForDisplay>() {
      @Override
      public CommentForDisplay mapRow(ResultSet rs, int rowNum) throws SQLException {
        CommentForDisplay comment = new CommentForDisplay();
        comment.id = rs.getLong("id");
        comment.textHtml = rs.getString("text_html");
        comment.username = rs.getString("username");
        comment.score = rs.getInt("score");
        comment.datePosted = rs.getDate("date_posted");
        return comment;
      }
    });
    return result;
  }

  @Override
  public Long createComment(CreateComment commentToCreate, User user) {
    if(user == null || !user.hasAllPrivileges(Privilege.ADD_COMMENT)) {
      throw new NotAuthorizedException();
    }
    Comment comment = new Comment();
    comment.setTextHtml(commentToCreate.textHtml);
    comment.setTextPlain(commentToCreate.textPlain);
    comment.setAnonymous(commentToCreate.anonymous);
    comment.setDatePosted(new Date());
    comment.setParentPublicationId(commentToCreate.parentPublicationId);
    comment.setUserId(user.getId());
    return commentService.createComment(comment);
  }

}
