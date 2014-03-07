package ee.teoreteetik.tt.dao.impl;

import ee.teoreteetik.tt.model.Topic;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

@Repository("topicDAO")
public class TopicDAO extends JdbcDaoSupport {

  @Autowired
  public TopicDAO(DataSource ds) {
    setDataSource(ds);
  }

  public List<Topic> loadSubjectTopics(Long subjectId) {
    String sql = "SELECT "
        + "         t.id, " 
        + "         t.title, "
        + "         t.text_plain,"
        + "         t.text_html, " 
        + "         t.subject_id, "
        + "         t.anonymous, " 
        + "         t.user_id, "
        + "         t.date_posted, "
        + "         u.name, "
        + "         COUNT(c.id) AS comment_count "
        + "       FROM   topic t "
        + "         LEFT JOIN users u ON t.user_id = u.id "
        + "         LEFT JOIN comment c ON c.topic_id = t.id "
        + "       WHERE  t.subject_id = ? " 
        + "       GROUP  BY t.id, "
        + "                 u.name "
        + "       ORDER BY t.date_posted DESC";
    List<Topic> topics = getJdbcTemplate().query(sql,
        new Object[] { subjectId }, new TopicRowMapper());
    return topics;
  }

  public Topic loadById(Long topicId) {
    String sql = "SELECT"
        + "         t.id, "
        + "         t.title,"
        + "         t.text_plain,"
        + "         t.text_html,"
        + "         t.anonymous,"
        + "         t.subject_id,"
        + "         t.user_id, "
        + "         t.date_posted,"
        + "         u.name,  "
        + "         COUNT(c.id) AS comment_count "
        + "       FROM topic t "
        + "         LEFT JOIN users u ON t.user_id = u.id "
        + "         LEFT JOIN comment c ON c.topic_id = t.id "
        + "       WHERE t.id = ? "
        + "       GROUP  BY t.id,"
        + "                 u.name";
    return DataAccessUtils.singleResult(getJdbcTemplate().query(sql,
        new Object[] { topicId }, new TopicRowMapper()));
  }


  private class TopicRowMapper implements ParameterizedRowMapper<Topic> {
    @Override
    public Topic mapRow(ResultSet rs, int rowNum) throws SQLException {
      Topic topic = new Topic();
      topic.setId(rs.getLong("id"));
      topic.setTitle(rs.getString("title"));
      topic.setTextPlain(rs.getString("text_plain"));
      topic.setTextHtml(rs.getString("text_html"));
      topic.setAnonymous(rs.getBoolean("anonymous"));
      topic.setSubjectId(rs.getLong("subject_id"));
      topic.setUserId(rs.getLong("user_id"));
      topic.setUserName(rs.getString("name"));
      topic.setDatePosted(rs.getTimestamp("date_posted"));
      topic.setCommentCount(rs.getInt("comment_count"));
      return topic;
    }
  }

  public Long create(Topic topic) {
    Long nextId = getJdbcTemplate().queryForLong("SELECT nextval('topic_id_seq')");
    String sql = "INSERT INTO topic (id, title, text_plain, text_html, anonymous, subject_id, user_id, date_posted) "
        + "       VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    getJdbcTemplate().update(sql, new Object[] {  nextId, 
                                                  topic.getTitle(),
                                                  topic.getTextPlain(),
                                                  topic.getTextHtml(),
                                                  topic.isAnonymous(), 
                                                  topic.getSubjectId(),
                                                  topic.getUserId(),
                                                  topic.getDatePosted() });
    return nextId;
  }
}