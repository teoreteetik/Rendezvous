package ee.teoreteetik.tt.client.service.impl;

import ee.teoreteetik.tt.client.exception.NotAuthorizedException;
import ee.teoreteetik.tt.client.model.SubjectRepresentation.CreateSubject;
import ee.teoreteetik.tt.client.model.SubjectRepresentation.SubjectForDisplay;
import ee.teoreteetik.tt.client.service.SubjectClientService;
import ee.teoreteetik.tt.internal.authentication.Privilege;
import ee.teoreteetik.tt.internal.model.Subject;
import ee.teoreteetik.tt.internal.model.User;
import ee.teoreteetik.tt.internal.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service("subjectClientService")
public class SubjectClientServiceImpl extends BaseClientServiceImpl implements SubjectClientService{

  @Autowired
  private SubjectService subjectService;

  @Override
  public List<SubjectForDisplay> getSubjectsBySemesterId(final Long semesterId) {
    String sql = ""
            + "SELECT s.id, "
            + "       s.name, "
            + "       s.code, "
            + "       COUNT(s.id) AS topic_count "
            + "FROM   subject s "
            + "LEFT JOIN topic t on t.subject_id = s.id "
            + "WHERE s.semester_id = ? "
            + "GROUP BY s.id, s.name, s.code";
    List<SubjectForDisplay> result = getJdbcTemplate().query(sql, new Object[] { semesterId }, new ParameterizedRowMapper<SubjectForDisplay>() {
      @Override
      public SubjectForDisplay mapRow(ResultSet rs, int rowNum) throws SQLException {
        SubjectForDisplay subject = new SubjectForDisplay();
        subject.id = rs.getLong("id");
        subject.name = rs.getString("name");
        subject.code = rs.getString("code");
        subject.topicCount = rs.getInt("topic_count");
        subject.semesterId = semesterId;
        return subject;
      }
    });
    return result;
  }

  @Override
  public Long createSubject(CreateSubject subjectToCreate, User user) {
    if(user == null || !user.hasAllPrivileges(Privilege.ADD_SUBJECT)) {
      throw new NotAuthorizedException();
    }
    Subject subject = new Subject();
    subject.setName(subjectToCreate.name);
    subject.setCode(subjectToCreate.code);
    subject.setSemesterId(subjectToCreate.semesterId);
    subject.setUserId(user.getId());
    return subjectService.createSubject(subject);
  }


}
