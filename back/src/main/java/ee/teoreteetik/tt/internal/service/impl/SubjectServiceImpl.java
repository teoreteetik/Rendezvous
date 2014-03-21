package ee.teoreteetik.tt.internal.service.impl;

import ee.teoreteetik.tt.internal.dao.SubjectDAO;
import ee.teoreteetik.tt.internal.service.SubjectService;
import ee.teoreteetik.tt.model.Semester;
import ee.teoreteetik.tt.model.Subject;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("subjectService")
public class SubjectServiceImpl implements SubjectService {

  @Autowired private SubjectDAO subjectDAO;

  @Override
  public List<Subject> getSubjectsBySemester(Semester semester) {
    return subjectDAO.loadSubjectsBySemester(semester);
  }

  @Override
  public Subject getSubjectById(Long subjectId) {
    return subjectDAO.loadSubjectById(subjectId);
  }

  @Override
  public Long createSubject(Subject subject) {
    Assert.assertFalse(StringUtils.isBlank(subject.getName()));
    Assert.assertFalse(StringUtils.isBlank(subject.getCode()));

    Long subjectId = subjectDAO.create(subject);
    return subjectId;
  }

  @Override
  public List<Semester> getSemesters() {
    return subjectDAO.getSemesters();
  }

}
