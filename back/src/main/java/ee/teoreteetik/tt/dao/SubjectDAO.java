package ee.teoreteetik.tt.dao;

import ee.teoreteetik.tt.model.Subject;
import java.util.List;

public interface SubjectDAO {
  List<Subject> loadSubjectsByYear(Long year);
}
