package ee.teoreteetik.tt.internal.dao;


import ee.teoreteetik.tt.internal.model.Comment;
import ee.teoreteetik.tt.internal.model.Topic;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository("commentDAO")
public class CommentDAO extends BaseDAO {

  public Long create(Comment comment) {
    String sql =
      "INSERT INTO comment " +
            "(publication_id, parent_publication_id) " +
             "VALUES (?, ?)";
    getJdbcTemplate().update(sql,
            comment.getId(),
            comment.getParentPublicationId());
    return comment.getId();
  }

}
