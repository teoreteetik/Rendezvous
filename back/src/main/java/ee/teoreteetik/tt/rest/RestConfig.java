package ee.teoreteetik.tt.rest;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.spring.scope.RequestContextFilter;

/**
 * Spring HelloWorld Web Application configuration.
 * 
 * @author Jakub Podlesak (jakub.podlesak at oracle.com)
 */
public class RestConfig extends ResourceConfig {

  /**
   * Register JAX-RS application components.
   */
  public RestConfig() {
    register(RequestContextFilter.class);
    register(UserRestService.class);
    register(SubjectRestService.class);
  }

}
