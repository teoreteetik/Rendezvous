package ee.teoreteetik.tt.client.service.impl;

import ee.teoreteetik.tt.client.model.SemesterRepresentation.SemesterForDisplay;
import ee.teoreteetik.tt.client.service.SemesterClientService;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service("semesterClientService")
public class SemesterClientServiceImpl extends BaseClientServiceImpl implements SemesterClientService{
  @Override
  public List<SemesterForDisplay> getSemesters() {
      String sql = ""
              + "SELECT s.id, "
              + "       s.text "
              + "FROM   semester s WHERE s.sys_deleted = FALSE";
      List<SemesterForDisplay> result = getJdbcTemplate().query(sql, new ParameterizedRowMapper<SemesterForDisplay>() {
        @Override
        public SemesterForDisplay mapRow(ResultSet rs, int rowNum) throws SQLException {
          SemesterForDisplay semester = new SemesterForDisplay();
          semester.id = rs.getLong("id");
          semester.text = rs.getString("text");
          return semester;
        }
      });
      return result;
  }

}
