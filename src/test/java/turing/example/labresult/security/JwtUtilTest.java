package turing.example.labresult.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit test for {@link JwtUtil} class.
 */
class JwtUtilTest {

    private JwtUtil jwtUtil;
    private static final String SECRET_KEY = "my-secret-key";

    /**
     * Sets up the test environment before each test execution.
     */
    @BeforeEach
    void setUp() {
        jwtUtil = new JwtUtil();
    }

    /**
     * Tests the {@link JwtUtil#generateToken(String)} method.
     * Ensures that a valid token is generated and can be parsed correctly.
     */
    @Test
    void testGenerateToken() {
        // Arrange
        String username = "test subject";

        // Act
        String token = jwtUtil.generateToken(username);

        // Assert
        assertNotNull(token, "Generated token should not be null");
        assertTrue(token.length() > 0, "Generated token should have a length greater than 0");

        // Validate token structure
        Claims claims = Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();

        assertEquals(username, claims.getSubject(), "Token subject should match the provided username");
        assertNotNull(claims.getIssuedAt(), "Token should have an issued-at date");
        assertNotNull(claims.getExpiration(), "Token should have an expiration date");
        assertTrue(claims.getExpiration().after(new Date()), "Token expiration date should be in the future");
    }
}
