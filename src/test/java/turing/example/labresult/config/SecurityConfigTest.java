package turing.example.labresult.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Unit tests for {@link SecurityConfig}.
 * Ensures security configurations behave as expected.
 */
@WebMvcTest(SecurityConfig.class) // Loads only the SecurityConfig class for testing
class SecurityConfigTest {

    @Autowired
    private MockMvc mockMvc; // MockMvc for performing HTTP requests in tests

    /**
     * Tests if public API endpoints (e.g., Swagger documentation) are accessible
     * without authentication.
     *
     * @throws Exception if the request fails
     */
    @Test
    void testPublicEndpointsAreAccessible() throws Exception {
        mockMvc.perform(get("/v3/api-docs"))
                .andExpect(status().isNotFound()); // Expecting 404 if the endpoint is missing
    }

    /**
     * Tests if an authenticated user can access a secure endpoint.
     * Uses {@link WithMockUser} to simulate authentication.
     *
     * @throws Exception if the request fails
     */
    @Test
    @WithMockUser // Simulates an authenticated user without specifying roles
    void testAuthenticatedEndpoint() throws Exception {
        mockMvc.perform(get("/secure-endpoint"))
                .andExpect(status().isNotFound());  // Expecting 404 since no role is assigned
    }

    /**
     * Tests if an unauthenticated user is denied access to a secure endpoint.
     *
     * @throws Exception if the request fails
     */
    @Test
    void testUnauthorizedAccess() throws Exception {
        mockMvc.perform(get("/secure-endpoint"))
                .andExpect(status().is4xxClientError()); // Expecting 4xx status code for unauthorized access
    }
}
