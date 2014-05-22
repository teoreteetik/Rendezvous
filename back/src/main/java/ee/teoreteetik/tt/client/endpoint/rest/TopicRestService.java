package ee.teoreteetik.tt.client.endpoint.rest;

import ee.teoreteetik.tt.client.model.CommentRepresentation.CreateComment;
import ee.teoreteetik.tt.client.model.TopicRepresentation.CreateTopic;
import ee.teoreteetik.tt.client.model.TopicRepresentation.TopicForDisplay;
import ee.teoreteetik.tt.client.service.CommentClientService;
import ee.teoreteetik.tt.client.service.TopicClientService;
import ee.teoreteetik.tt.internal.model.User;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.net.URI;

@Path("/topics")
@Produces(MediaType.APPLICATION_JSON)
public class TopicRestService extends RestService {

  @Autowired
  private TopicClientService topicClientService;
  @Autowired
  private CommentClientService commentClientService;

  @GET
  @Path("{topicId}")
  @Produces(MediaType.APPLICATION_JSON)
  public TopicForDisplay getTopicById(@PathParam("topicId") final Long topicId) {
    return topicClientService.getTopicForDisplay(topicId);
  }

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public Response createTopic(@Context HttpHeaders headers, final CreateTopic topicToCreate) {
    User user = getUser(headers);
    Long id = topicClientService.createTopic(topicToCreate, user);
    URI uri = UriBuilder.fromPath(id.toString()).build();
    return Response.created(uri).entity(id).build();
  }

  @POST
  @Path("{topicId}/comments")
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public Response createComment(@Context HttpHeaders headers, CreateComment commentToCreate) {
    User user = getUser(headers);
    Long id = commentClientService.createComment(commentToCreate, user);
    return Response.ok(id).build();
  }

  @DELETE
  @Path("{topicId}/comments/{commentId}")
  public Response deleteComment(@Context HttpHeaders headers, @PathParam("commentId") final Long commentId) {
    User user = getUser(headers);
    commentClientService.deleteComment(commentId, user);
    return Response.ok().build();
  }

  @DELETE
  @Path("{topicId}")
  public Response deleteTopic(@Context HttpHeaders headers, @PathParam("topicId") final Long topicId) {
    User user = getUser(headers);
    topicClientService.deleteTopic(topicId, user);
    return Response.ok().build();
  }

}
