package ee.teoreteetik.tt.internal.dao;

import ee.teoreteetik.tt.model.Semester;
import ee.teoreteetik.tt.model.Subject;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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

  public List<Subject> loadSubjectsBySemester(Semester semester) {
    List<Object> params = new ArrayList<>();
    StringBuilder sql = new StringBuilder("SELECT * FROM subject su INNER JOIN semester se on su.semester_id = se.id WHERE 1 = 1");
    if (semester.getYear() != null) {
      sql.append(" AND se.year = ?");
      params.add(semester.getYear());
    }
    if (semester.getSemesterNumber() != null) {
      sql.append(" AND se.semester_number = ?");
      params.add(semester.getSemesterNumber());
    }
    return getJdbcTemplate().query(sql.toString(), params.toArray(), new SubjectRowMapper());
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
      subject.setSemesterId(rs.getLong("semester_id"));
      return subject;
    }
  }

  public Long create(Subject subject) {
    String sql = "INSERT INTO subject (name, code, semester_id) VALUES (?, ?, ?) RETURNING id";
    Long id = getJdbcTemplate().queryForLong(sql, new Object[] {
        subject.getName(),
        subject.getCode(),
        subject.getSemesterId() });
    return id;
  }

  public List<Semester> getSemesters() {
    String sql = "SELECT * FROM semester ORDER BY year DESC, semester_number DESC";
    return getJdbcTemplate().query(sql, new Object[] {}, new ParameterizedRowMapper<Semester>() {
      @Override
      public Semester mapRow(ResultSet rs, int rowNum) throws SQLException {
        Semester semester = new Semester();
        semester.setId(rs.getLong("id"));
        semester.setYear(rs.getInt("year"));
        semester.setSemesterNumber(rs.getInt("semester_number"));
        semester.setText(rs.getString("text"));
        return semester;
      }
    });
  }
}
