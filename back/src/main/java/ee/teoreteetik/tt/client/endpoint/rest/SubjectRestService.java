package ee.teoreteetik.tt.client.endpoint.rest;

import ee.teoreteetik.tt.client.model.SubjectRepresentation.CreateSubject;
import ee.teoreteetik.tt.client.model.SubjectRepresentation.SubjectForDisplay;
import ee.teoreteetik.tt.client.model.TopicRepresentation.TopicForListDisplay;
import ee.teoreteetik.tt.client.service.SubjectClientService;
import ee.teoreteetik.tt.client.service.TopicClientService;
import ee.teoreteetik.tt.internal.model.User;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/subjects")
@Produces(MediaType.APPLICATION_JSON)
public class SubjectRestService extends RestService {

  @Autowired
  private SubjectClientService subjectClientService;
  @Autowired
  private TopicClientService topicClientService;

  @GET
  @Path("{subjectId}/topics")
  @Produces(MediaType.APPLICATION_JSON)
  public Response getSubjectTopics(@PathParam("subjectId") final Long subjectId) {
    return Response.ok(topicClientService.getTopicsBySubjectId(subjectId)).build();
  }

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public Response createSubject(@Context HttpHeaders headers, final CreateSubject subjectToCreate) {
    User user = getUser(headers);
    Long id = subjectClientService.createSubject(subjectToCreate, user);
    return Response.ok(id).build();
  }
}
