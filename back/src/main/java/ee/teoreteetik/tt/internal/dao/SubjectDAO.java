package ee.teoreteetik.tt.internal.dao;

import ee.teoreteetik.tt.internal.model.Semester;
import ee.teoreteetik.tt.internal.model.Subject;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

@Repository("subjectDAO")
public class SubjectDAO extends BaseDAO {

  public Long create(Subject subject) {
    String sql = "INSERT INTO subject (name, code, semester_id, user_id) VALUES (?, ?, ?, ?) RETURNING id";
    Long id = getJdbcTemplate().queryForLong(sql, new Object[] {
        subject.getName(),
        subject.getCode(),
        subject.getSemesterId(),
        subject.getUserId()});
    return id;
  }
}
