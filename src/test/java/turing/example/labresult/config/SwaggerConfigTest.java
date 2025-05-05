package turing.example.labresult.config;

import io.swagger.v3.oas.models.OpenAPI;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Unit tests for {@link SwaggerConfig}.
 * Ensures that the OpenAPI bean is correctly configured.
 */
class SwaggerConfigTest {

    /**
     * Tests if the OpenAPI bean is correctly initialized with the expected properties.
     */
    @Test
    void testOpenAPIBean() {
        SwaggerConfig swaggerConfig = new SwaggerConfig(); // Creates an instance of SwaggerConfig
        OpenAPI openAPI = swaggerConfig.openAPI(); // Calls the openAPI method to get the OpenAPI bean

        assertThat(openAPI).isNotNull(); // Asserts that OpenAPI object is not null
        assertThat(openAPI.getInfo()).isNotNull(); // Asserts that OpenAPI info is not null
        assertThat(openAPI.getInfo().getTitle()).isEqualTo("Lab Results API"); // Checks if title is correctly set
        assertThat(openAPI.getInfo().getVersion()).isEqualTo("1.0"); // Verifies version is as expected
        assertThat(openAPI.getInfo().getDescription()).isEqualTo("API for managing laboratory results"); // Checks if description matches
    }
}
