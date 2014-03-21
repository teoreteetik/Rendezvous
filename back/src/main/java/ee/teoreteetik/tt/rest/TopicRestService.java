package ee.teoreteetik.tt.rest;

import ee.teoreteetik.tt.internal.authentication.Kubjas;
import ee.teoreteetik.tt.internal.service.TopicService;
import ee.teoreteetik.tt.model.Topic;
import ee.teoreteetik.tt.socket.TopicSocket;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.springframework.beans.factory.annotation.Autowired;

@Path("/topics")
@Produces(MediaType.APPLICATION_JSON)
public class TopicRestService {

  @Autowired private TopicService topicService;

  @GET
  @Path("/{topicId}")
  @Produces(MediaType.APPLICATION_JSON)
  public Topic getTopicById(@Context HttpHeaders headers, @PathParam("topicId") final Long topicId,
      @DefaultValue("false") @QueryParam("forEdit") boolean forEdit) {
    return topicService.getById(topicId, forEdit);
  }

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public Response createTopic(@Context HttpHeaders headers, final Topic topic) {
    List<String> uuids = headers.getRequestHeader("Authorization");
    if (uuids == null || Kubjas.getUser(uuids.get(0)) == null) {
      return Response.status(401).build();
    }
    String uuid = uuids.get(0);
    topic.setUserId(Kubjas.getUser(uuid).getId());
    Long topicId = topicService.createTopic(topic);
    TopicSocket.sendTopicAlert(topic);
    return Response.status(201).entity(topicId).build();
  }
}
