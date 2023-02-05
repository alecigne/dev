package net.lecigne.dev;

import io.micronaut.runtime.Micronaut;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
    info = @Info(
        title = "Read Excel with Apache POI",
        version = "0.0.1",
        description = "An API that reads an Excel file and persist the data in a database"
    )
)
public class Application {

  public static void main(String[] args) {
    Micronaut.run(Application.class, args);
  }

}
