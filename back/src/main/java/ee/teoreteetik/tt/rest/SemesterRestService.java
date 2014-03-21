package ee.teoreteetik.tt.rest;

import ee.teoreteetik.tt.internal.service.SubjectService;
import ee.teoreteetik.tt.model.Semester;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.springframework.beans.factory.annotation.Autowired;

@Path("semesters")
@Produces(MediaType.APPLICATION_JSON)
public class SemesterRestService {

  @Autowired private SubjectService subjectService;

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public List<Semester> getSemesters() {
    List<Semester> semesters = subjectService.getSemesters();
    return semesters;
  }

}
