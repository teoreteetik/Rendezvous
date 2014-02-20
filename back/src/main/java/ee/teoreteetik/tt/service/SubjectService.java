package ee.teoreteetik.tt.service;

import ee.teoreteetik.tt.model.Subject;
import java.util.List;

public interface SubjectService {
  List<Subject> getSubjectsByYear(Long year);
}
