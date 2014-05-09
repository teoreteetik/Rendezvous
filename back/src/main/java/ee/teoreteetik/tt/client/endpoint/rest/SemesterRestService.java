package ee.teoreteetik.tt.client.endpoint.rest;

import ee.teoreteetik.tt.client.model.SemesterRepresentation.SemesterForDisplay;
import ee.teoreteetik.tt.client.model.SubjectRepresentation.SubjectForDisplay;
import ee.teoreteetik.tt.client.service.SemesterClientService;
import ee.teoreteetik.tt.client.service.SubjectClientService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/semesters")
@Produces(MediaType.APPLICATION_JSON)
public class SemesterRestService extends RestService {

  @Autowired
  private SubjectClientService subjectClientService;
  @Autowired
  private SemesterClientService semesterClientService;

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public Response getSemesters() {
    List<SemesterForDisplay> result = semesterClientService.getSemesters();
    return Response.ok(result).build();
  }

  @GET
  @Path("{semesterId}/subjects")
  @Produces(MediaType.APPLICATION_JSON)
  public Response getSemesterSubjects(@PathParam("semesterId") final Long semesterId) {
    List<SubjectForDisplay> result = subjectClientService.getSubjectsBySemesterId(semesterId);
    return Response.ok(result).build();
  }


}
