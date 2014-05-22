package ee.teoreteetik.tt.client.service.impl;

import ee.teoreteetik.tt.client.exception.NotAuthorizedException;
import ee.teoreteetik.tt.client.model.CommentRepresentation.CommentForDisplay;
import ee.teoreteetik.tt.client.model.TopicRepresentation.CreateTopic;
import ee.teoreteetik.tt.client.model.TopicRepresentation.TopicForListDisplay;
import ee.teoreteetik.tt.client.model.TopicRepresentation.TopicForDisplay;
import ee.teoreteetik.tt.client.service.CommentClientService;
import ee.teoreteetik.tt.client.service.TopicClientService;
import ee.teoreteetik.tt.internal.authentication.Privilege;
import ee.teoreteetik.tt.internal.model.Topic;
import ee.teoreteetik.tt.internal.model.User;
import ee.teoreteetik.tt.internal.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.stereotype.Service;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

@Service("topicClientService")
public class TopicClientServiceImpl extends BaseClientServiceImpl implements TopicClientService {

  @Autowired
  private TopicService topicService;

  @Autowired
  private CommentClientService commentClientService;

  @Override
  public TopicForDisplay getTopicForDisplay(Long topicId) {
    String sql = ""
            + "SELECT t.id, "
            + "       t.title, "
            + "       t.text_html, "
            + "       CASE "
            + "         WHEN t.anonymous = TRUE THEN 'Peeter Paan' "
            + "         ELSE t.username "
            + "       END AS username, "
            + "       t.date_posted "
            + "FROM   v_topic t "
            + "WHERE  t.id = ? ";

    TopicForDisplay result = DataAccessUtils.singleResult(getJdbcTemplate().query(sql,
            new Object[]{ topicId }, new ParameterizedRowMapper<TopicForDisplay>() {
              @Override
              public TopicForDisplay mapRow(ResultSet rs, int rowNum) throws SQLException {
                TopicForDisplay topic = new TopicForDisplay();
                topic.id = rs.getLong("id");
                topic.title = rs.getString("title");
                topic.textHtml = rs.getString("text_html");
                topic.username = rs.getString("username");
                topic.datePosted = rs.getTimestamp("date_posted");
                return topic;
              }
            }));
    List<CommentForDisplay> comments = commentClientService.getCommentsForTopic(result.id);
    result.comments = comments;
    return result;
  }

  @Override
  public List<TopicForListDisplay> getTopicsBySubjectId(Long subjectId) {
    String sql = ""
            + "SELECT t.id, "
            + "       t.title, "
            + "       t.text_html, "
            + "       CASE "
            + "         WHEN t.anonymous = TRUE THEN 'Peeter Paan' "
            + "         ELSE t.username "
            + "       END AS username, "
            + "       t.date_posted,"
            + "       t.comment_count "
            + "FROM   v_topic t "
            + "WHERE  t.subject_id = ? ";

    List<TopicForListDisplay> result = getJdbcTemplate().query(sql,
            new Object[]{subjectId}, new ParameterizedRowMapper<TopicForListDisplay>() {
              @Override
              public TopicForListDisplay mapRow(ResultSet rs, int rowNum) throws SQLException {
                TopicForListDisplay topic = new TopicForListDisplay();
                topic.id = rs.getLong("id");
                topic.title = rs.getString("title");
                topic.textHtml = rs.getString("text_html");
                topic.username = rs.getString("username");
                topic.datePosted = rs.getTimestamp("date_posted");
                topic.commentCount = rs.getInt("comment_count");
                return topic;
              }
            }
    );
    return result;
  }

  @Override
  public Long createTopic(CreateTopic topicToCreate, User user) {
    if(user == null || !user.hasAllPrivileges(Privilege.ADD_TOPIC)) {
      throw new NotAuthorizedException();
    }
    Topic topic = new Topic();
    topic.setTitle(topicToCreate.title);
    topic.setTextHtml(topicToCreate.textHtml);
    topic.setTextPlain(topicToCreate.textPlain);
    topic.setDatePosted(new Date());
    topic.setAnonymous(topicToCreate.anonymous);
    topic.setSubjectId(topicToCreate.subjectId);
    topic.setUserId(user.getId());
    return topicService.createTopic(topic);
  }

  @Override
  public void deleteTopic(Long topicId, User user) {
    if(user == null || !user.hasAllPrivileges(Privilege.DELETE_FOREIGN_TOPIC)) {
      throw new NotAuthorizedException();
    }
    topicService.deleteTopic(topicId);
  }
}
