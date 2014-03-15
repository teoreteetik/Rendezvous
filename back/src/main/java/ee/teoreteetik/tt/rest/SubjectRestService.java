package ee.teoreteetik.tt.rest;

import ee.teoreteetik.tt.model.Semester;
import ee.teoreteetik.tt.model.Subject;
import ee.teoreteetik.tt.model.Topic;
import ee.teoreteetik.tt.service.SubjectService;
import ee.teoreteetik.tt.service.TopicService;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.springframework.beans.factory.annotation.Autowired;

@Path("subjects")
@Produces(MediaType.APPLICATION_JSON)
public class SubjectRestService {

  @Autowired private SubjectService subjectService;
  @Autowired private TopicService   topicService;

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public List<Subject> getSubjectsBySemester(
      @QueryParam(value = "year") final Integer year,
      @QueryParam(value = "semesterNumber") final Integer semesterNumber) {
    Semester semester = new Semester();
    semester.setYear(year);
    semester.setSemesterNumber(semesterNumber);
    List<Subject> subjects = subjectService.getSubjectsBySemester(semester);
    return subjects;
  }

  @GET
  @Path("/{subjectId}")
  @Produces(MediaType.APPLICATION_JSON)
  public Subject getSubjectById(@PathParam("subjectId") final Long subjectId) {
    Subject subject = subjectService.getSubjectById(subjectId);
    return subject;
  }

  @GET
  @Path("/{subjectId}/topics")
  @Produces(MediaType.APPLICATION_JSON)
  public List<Topic> getSubjectTopics(
      @PathParam("subjectId") final Long subjectId) {
    return topicService.getBySubjectId(subjectId);
  }

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public Response createSubject(final Subject subject) {
    Long subjectId = subjectService.createSubject(subject);
    return Response.status(201).entity(subjectId).build();
  }
}
