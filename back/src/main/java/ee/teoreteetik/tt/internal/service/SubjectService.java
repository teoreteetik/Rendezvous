package ee.teoreteetik.tt.internal.service;

import ee.teoreteetik.tt.model.Semester;
import ee.teoreteetik.tt.model.Subject;
import java.util.List;

public interface SubjectService {
  List<Subject> getSubjectsBySemester(Semester semester);

  Subject getSubjectById(Long subjectId);

  Long createSubject(Subject subject);

  List<Semester> getSemesters();
}
