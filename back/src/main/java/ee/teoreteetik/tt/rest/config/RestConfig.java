package ee.teoreteetik.tt.rest.config;

import ee.teoreteetik.tt.rest.SemesterRestService;
import ee.teoreteetik.tt.rest.SubjectRestService;
import ee.teoreteetik.tt.rest.TopicRestService;
import ee.teoreteetik.tt.rest.UserRestService;
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

  }

}
