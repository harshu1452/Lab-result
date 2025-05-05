package turing.example.labresult.security;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.userdetails.UserDetails;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit test for {@link UserDetailsServiceImpl} class.
 * This class tests the functionality of loading user details.
 */
class UserDetailsServiceImplTest {

    private UserDetailsServiceImpl userDetailsService;

    /**
     * Sets up the test environment before each test.
     * Initializes the {@link UserDetailsServiceImpl} instance.
     */
    @BeforeEach
    void setUp() {
        userDetailsService = new UserDetailsServiceImpl();
    }

    /**
     * Tests the {@code loadUserByUsername} method to ensure it correctly loads user details.
     * Verifies that the returned {@link UserDetails} object contains expected data.
     */
    @Test
    void testLoadUserByUsername() {
        // Arrange
        String username = "testUser";

        // Act
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        // Assert
        assertNotNull(userDetails, "UserDetails should not be null");
        assertEquals(username, userDetails.getUsername(), "Username should match");
        assertEquals("{noop}password", userDetails.getPassword(), "Password should match expected value");
        assertTrue(userDetails.getAuthorities().isEmpty(), "Authorities list should be empty");
    }
}
