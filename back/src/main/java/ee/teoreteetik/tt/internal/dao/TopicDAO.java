package ee.teoreteetik.tt.internal.dao;

import ee.teoreteetik.tt.internal.model.Topic;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

@Repository("topicDAO")
public class TopicDAO extends BaseDAO {

  public Long create(Topic topic) {
    String sql =
            "INSERT INTO topic " +
            "(publication_id, title, subject_id) " +
            "VALUES (?, ?, ?)";
    getJdbcTemplate().update(sql,
                              topic.getId(),
                              topic.getTitle(),
                              topic.getSubjectId());
    return topic.getId();
  }
}