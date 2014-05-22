package ee.teoreteetik.tt.client.service;

import ee.teoreteetik.tt.client.model.SubjectRepresentation.CreateSubject;
import ee.teoreteetik.tt.client.model.SubjectRepresentation.SubjectForDisplay;
import ee.teoreteetik.tt.internal.model.User;

import java.util.List;

public interface SubjectClientService {

  List<SubjectForDisplay> getSubjectsBySemesterId(Long semesterId);
  Long createSubject(CreateSubject subjectToCreate, User user);
  void deleteSubject(Long subjectId, User user);
}
