package ee.teoreteetik.tt.rest;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;
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

@Api(value = "subjects", description = "Operations on subjects")
@Path("subjects")
@Produces(MediaType.APPLICATION_JSON)
public class SubjectRestService {

  @Autowired
  private SubjectService subjectService;
  @Autowired
  private TopicService topicService;

  @ApiOperation(value = "Find subjects by year and semester number")
  @ApiResponses(value = {
      @ApiResponse(code = 400, message = "Invalid parameters")}) //TODO
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public List<Subject> getSubjectsBySemester(
      @ApiParam(value="4-digit year number") @QueryParam(value = "year") final int year,
      @ApiParam(value="Semester number - 1 or 2")             @QueryParam(value = "semester") final int semester) {
    List<Subject> subjects = subjectService.getSubjectsBySemester(year,
        semester);
    return subjects;
  }
  
  @ApiOperation(value = "Find subject by ID", response = Subject.class)
  @ApiResponses(value = {
      @ApiResponse(code = 400, message = "Invalid ID supplied"),
      @ApiResponse(code = 404, message = "Subject not found")}) //TODO response codes
  @GET
  @Path("/{subjectId}")
  @Produces(MediaType.APPLICATION_JSON)
  public Subject getSubjectById(
      @ApiParam(value = "ID of Subject", required = true) 
        @PathParam("subjectId") final Long subjectId) {
    Subject subject = subjectService.getSubjectById(subjectId);
    return subject;
  }

  @ApiOperation(value = "Find Subject Topics by Subject ID")
  @ApiResponses(value = {
      @ApiResponse(code = 400, message = "Invalid ID supplied")})
  @GET
  @Path("/{subjectId}/topics")
  @Produces(MediaType.APPLICATION_JSON)
  public List<Topic> getSubjectTopics(
      @ApiParam(value = "ID of Subject", required = true) 
        @PathParam("subjectId") final Long subjectId) {
    return topicService.getBySubjectId(subjectId);
  }

  @ApiOperation(value = "Create Subject")
  @ApiResponses(value = {
      @ApiResponse(code = 201, message = "Subject created")})
  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public Response createSubject(
      @ApiParam(value = "Subject object") final Subject subject) {
    Long subjectId = subjectService.createSubject(subject);
    return Response.status(201).entity(subjectId).build();
  }
}
