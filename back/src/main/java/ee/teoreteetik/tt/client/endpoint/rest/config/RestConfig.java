package ee.teoreteetik.tt.client.endpoint.rest.config;

import ee.teoreteetik.tt.client.endpoint.rest.AuthRestService;
import ee.teoreteetik.tt.client.endpoint.rest.SubjectRestService;
import ee.teoreteetik.tt.client.endpoint.rest.TopicRestService;
import ee.teoreteetik.tt.client.endpoint.rest.SemesterRestService;
import ee.teoreteetik.tt.client.endpoint.rest.UserRestService;

import ee.teoreteetik.tt.client.endpoint.rest.provider.NotAuthorizedExceptionMapper;
import org.glassfish.jersey.server.ResourceConfig;

/**
 * @author Jakub Podlesak (jakub.podlesak at oracle.com)
 */
public class RestConfig extends ResourceConfig {

  public RestConfig() {
    register(UserRestService.class);
    register(SubjectRestService.class);
    register(TopicRestService.class);
    register(SemesterRestService.class);
    register(AuthRestService.class);
    register(NotAuthorizedExceptionMapper.class);
  }
}
