package ee.teoreteetik.tt.rest.config;

import com.wordnik.swagger.config.ConfigFactory;
import com.wordnik.swagger.config.ScannerFactory;
import com.wordnik.swagger.config.SwaggerConfig;
import com.wordnik.swagger.jaxrs.config.ReflectiveJaxrsScanner;
import com.wordnik.swagger.jaxrs.reader.DefaultJaxrsApiReader;
import com.wordnik.swagger.reader.ClassReaders;

public class SwaggerConfigurer {
  public void init() {
    ReflectiveJaxrsScanner scanner = new ReflectiveJaxrsScanner();
    scanner.setResourcePackage("ee.teoreteetik.tt.rest");
    ScannerFactory.setScanner(scanner);
    ClassReaders.setReader(new DefaultJaxrsApiReader());

    // Set the swagger config options
    SwaggerConfig config = ConfigFactory.config();
    config.setApiVersion("1.0.0");
    config.setBasePath("/back/rest");
  }
}
