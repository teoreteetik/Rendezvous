package ee.teoreteetik.tt.dao.impl;

import ee.teoreteetik.tt.model.Subject;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

@Repository("subjectDAO")
public class SubjectDAO extends JdbcDaoSupport {

  @Autowired
  public SubjectDAO(DataSource ds) {
    setDataSource(ds);
  }
  
  public List<Subject> loadSubjectsBySemester(int year, int semester) {
    String sql = "SELECT * FROM subject s";
    if (year != 0 && semester != 0) {
      sql += " WHERE s.year = ? AND s.semester = ?"; //TODO
      return getJdbcTemplate().query(sql, new Object[] { year, semester }, new SubjectRowMapper());
    }
    return getJdbcTemplate().query(sql, new Object[] {}, new SubjectRowMapper());
  }

  public Subject loadSubjectById(Long subjectId) {
    String sql = "SELECT * FROM subject s WHERE s.id = ?";
    return DataAccessUtils.singleResult(getJdbcTemplate().query(sql, new Object[] { subjectId }, new SubjectRowMapper()));
  }

  private class SubjectRowMapper implements ParameterizedRowMapper<Subject> {
    @Override
    public Subject mapRow(ResultSet rs, int rowNum) throws SQLException {
      Subject subject = new Subject();
      subject.setId(rs.getLong("id"));
      subject.setName(rs.getString("name"));
      subject.setCode(rs.getString("code"));
      subject.setYear(rs.getInt("year"));
      subject.setSemester(rs.getInt("semester"));
      return subject;
    }
  }

  public Long create(Subject subject) {
    Long nextId = getJdbcTemplate().queryForLong("SELECT nextval('subject_id_seq')");
    String sql = "INSERT INTO subject (id, name, code, year, semester) VALUES (?, ?, ?, ?, ?)";
    getJdbcTemplate().update(sql, new Object[] {  nextId,
                                                  subject.getName(), 
                                                  subject.getCode(),
                                                  subject.getYear(), 
                                                  subject.getSemester() });
    return nextId;
  }
}
