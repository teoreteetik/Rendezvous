package ee.teoreteetik.tt.dao.impl;

import ee.teoreteetik.tt.dao.SubjectDAO;
import ee.teoreteetik.tt.model.Subject;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

@Repository("subjectDAO")
public class SubjectDAOImpl extends JdbcDaoSupport implements SubjectDAO {

  @Override
  public List<Subject> loadSubjectsByYear(Long year) {
    String sql = "select * from SUBJECT where year = ?";
    List<Subject> subjects = getJdbcTemplate().query(sql, new Object[] { year }, 
        new ParameterizedRowMapper<Subject>() {
          @Override
          public Subject mapRow(ResultSet rs, int rowNum) throws SQLException {
            Subject subject = new Subject();
            subject.setCode(rs.getString("CODE"));
            subject.setName(rs.getString("NAME"));
            subject.setYear(rs.getLong("YEAR"));
            subject.setId(rs.getLong("ID"));
            return subject;
          }
        });
    return subjects;
  }

  @Autowired
  public SubjectDAOImpl(DataSource ds) {
    setDataSource(ds);
  }

}
