package ee.teoreteetik.tt.rest;

import com.wordnik.swagger.annotations.Api;

import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;
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


@Api(value = "topics", description = "Operations on topics")
@Path("/topics")
@Produces(MediaType.APPLICATION_JSON)
public class TopicRestService {

  @Autowired
  private TopicService topicService;

  @ApiOperation(value = "Find topic by ID", response = Topic.class)
  @ApiResponses(value = {
      @ApiResponse(code = 400, message = "Invalid ID supplied"),
      @ApiResponse(code = 404, message = "Topic not found")}) //TODO response codes
  @GET
  @Path("/{topicId}")
  @Produces(MediaType.APPLICATION_JSON)
  public Topic getTopicById(
      @ApiParam(value = "ID of Topic", required = true)                                                                                   
        @PathParam("topicId") final Long topicId,
      @ApiParam(value = "Whether to include plaintext for editing or HTML for displaying", defaultValue = "false") 
        @DefaultValue("false") @QueryParam("forEdit") boolean forEdit) {
    return topicService.getById(topicId, forEdit);
  }

  @ApiOperation(value = "Create new topic")
  @ApiResponses(value = { @ApiResponse(code = 201, message = "Topic successfully created") })
  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public Response createSubject(
      @ApiParam(value = "Topic object") final Topic topic) {
    Long topicId = topicService.createTopic(topic);
    return Response.status(201).entity(topicId).build();
  }
}
