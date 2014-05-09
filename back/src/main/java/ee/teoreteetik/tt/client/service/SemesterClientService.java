package ee.teoreteetik.tt.client.service;

import ee.teoreteetik.tt.client.model.SemesterRepresentation.SemesterForDisplay;
import java.util.List;


public interface SemesterClientService {

  List<SemesterForDisplay> getSemesters();

}
