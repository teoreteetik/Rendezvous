package ee.teoreteetik.tt.rest.config;

import ee.teoreteetik.tt.rest.SubjectRestService;
import ee.teoreteetik.tt.rest.TopicRestService;
import ee.teoreteetik.tt.rest.UserRestService;

import com.wordnik.swagger.jaxrs.listing.ApiDeclarationProvider;
import com.wordnik.swagger.jaxrs.listing.ApiListingResourceJSON;
import com.wordnik.swagger.jaxrs.listing.ResourceListingProvider;
import org.glassfish.jersey.server.ResourceConfig;

/**
 * @author Jakub Podlesak (jakub.podlesak at oracle.com)
 */
public class RestConfig extends ResourceConfig {

  public RestConfig() {
    register(UserRestService.class);
    register(SubjectRestService.class);
    register(TopicRestService.class);

    // Swagger-specific
    register(ApiListingResourceJSON.class);
    register(ApiDeclarationProvider.class);
    register(ResourceListingProvider.class);
    register(SwaggerConfigurer.class);
  }

}
