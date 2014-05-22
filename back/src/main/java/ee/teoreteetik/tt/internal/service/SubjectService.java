package ee.teoreteetik.tt.internal.service;

import ee.teoreteetik.tt.internal.model.Semester;
import ee.teoreteetik.tt.internal.model.Subject;
import java.util.List;

public interface SubjectService {

  Long createSubject(Subject subject);
  void deleteSubject(Long subjectId);

}
