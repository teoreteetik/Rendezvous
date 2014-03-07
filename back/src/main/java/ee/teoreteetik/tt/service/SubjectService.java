package ee.teoreteetik.tt.service;

import ee.teoreteetik.tt.model.Subject;
import java.util.List;

public interface SubjectService {
  List<Subject> getSubjectsBySemester(int year, int semester);

  Subject getSubjectById(Long subjectId);

  Long createSubject(Subject subject);
}
