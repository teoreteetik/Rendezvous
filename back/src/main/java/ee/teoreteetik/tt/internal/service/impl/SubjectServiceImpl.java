package ee.teoreteetik.tt.internal.service.impl;

import ee.teoreteetik.tt.internal.dao.SubjectDAO;
import ee.teoreteetik.tt.internal.model.Subject;
import ee.teoreteetik.tt.internal.service.SubjectService;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("subjectService")
public class SubjectServiceImpl implements SubjectService {

  @Resource private SubjectDAO subjectDAO;

  @Override
  public Long createSubject(Subject subject) {
    Validate.isTrue(!StringUtils.isBlank(subject.getName()));
    Validate.isTrue(!StringUtils.isBlank(subject.getCode()));
    return subjectDAO.create(subject);
  }


}
