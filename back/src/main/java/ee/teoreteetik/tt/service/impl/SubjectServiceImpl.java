package ee.teoreteetik.tt.service.impl;

import ee.teoreteetik.tt.dao.SubjectDAO;
import ee.teoreteetik.tt.model.Subject;
import ee.teoreteetik.tt.service.SubjectService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("subjectService")
public class SubjectServiceImpl implements SubjectService {

  @Autowired
  private SubjectDAO subjectDAO;

  @Override
  public List<Subject> getSubjectsByYear(Long year) {
    return subjectDAO.loadSubjectsByYear(year);
  }

}
