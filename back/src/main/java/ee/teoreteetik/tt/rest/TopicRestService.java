package ee.teoreteetik.tt.rest;

import ee.teoreteetik.tt.model.Topic;
import ee.teoreteetik.tt.service.TopicService;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.springframework.beans.factory.annotation.Autowired;

@Path("/topics")
@Produces(MediaType.APPLICATION_JSON)
public class TopicRestService {

  @Autowired
  private TopicService topicService;

  @GET
  @Path("/{topicId}")
  @Produces(MediaType.APPLICATION_JSON)
  public Topic getTopicById(@PathParam("topicId") final Long topicId,
      @DefaultValue("false") @QueryParam("forEdit") boolean forEdit) {
    return topicService.getById(topicId, forEdit);
  }

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public Response createSubject(final Topic topic) {
    Long topicId = topicService.createTopic(topic);
    return Response.status(201).entity(topicId).build();
  }
}
