package turing.example.labresult.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for Swagger/OpenAPI documentation.
 * This class sets up OpenAPI specifications for documenting the Lab Results API.
 */
@Configuration // Marks this class as a Spring configuration class
public class SwaggerConfig {

    /**
     * Configures the OpenAPI documentation for the application.
     *
     * @return an instance of {@link OpenAPI} containing API metadata
     */
    @Bean // Defines a bean for OpenAPI configuration
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info()
                        /**
                         * Sets the API title.
                         * This title appears in the generated API documentation.
                         */
                        .title("Lab Results API")

                        /**
                         * Specifies the API version.
                         * Useful for tracking changes and updates to the API.
                         */
                        .version("1.0")

                        /**
                         * Provides a brief description of the API.
                         * This helps users understand the purpose and functionality of the API.
                         */
                        .description("API for managing laboratory results"));
    }
}
