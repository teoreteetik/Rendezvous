package ee.teoreteetik.tt.service.impl;

import ee.teoreteetik.tt.dao.impl.SubjectDAO;
import ee.teoreteetik.tt.model.Subject;
import ee.teoreteetik.tt.service.SubjectService;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("subjectService")
public class SubjectServiceImpl implements SubjectService {

  @Autowired
  private SubjectDAO subjectDAO;

  @Override
  public List<Subject> getSubjectsBySemester(int year, int semester) {
    return subjectDAO.loadSubjectsBySemester(year, semester);
  }

  @Override
  public Subject getSubjectById(Long subjectId) {
    return subjectDAO.loadSubjectById(subjectId);
  }

  @Override
  public Long createSubject(Subject subject) {
    Assert.assertFalse(StringUtils.isBlank(subject.getName()));
    Assert.assertFalse(StringUtils.isBlank(subject.getCode()));
    Assert.assertTrue(subject.getSemester() == 1 || subject.getSemester() == 2);
    Assert.assertTrue(subject.getYear() == 2013L); // TODO

    Long subjectId = subjectDAO.create(subject);
    return subjectId;
  }

}
