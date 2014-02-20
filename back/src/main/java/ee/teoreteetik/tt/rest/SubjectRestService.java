package ee.teoreteetik.tt.rest;

import ee.teoreteetik.tt.model.Subject;
import ee.teoreteetik.tt.service.SubjectService;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.springframework.beans.factory.annotation.Autowired;

@Path("/subjects")
public class SubjectRestService {

  @Autowired
  private SubjectService subjectService;

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public List<Subject> getSubjectsByYear() {
    List<Subject> subjects = subjectService.getSubjectsByYear(2013L);
    return subjects;
  }
}
