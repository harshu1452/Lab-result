package turing.example.labresult.security;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Utility class for handling JWT token generation.
 * This class provides methods to generate JWT tokens for authentication.
 */
@Component // Marks this class as a Spring-managed component
public class JwtUtil {

    private static final String SECRET_KEY = "my-secret-key"; // Secret key for signing JWT tokens

    /**
     * Generates a JWT token for a given username.
     *
     * @param username the username for whom the token is generated
     * @return a signed JWT token as a String
     */
    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject("test subject") // Sets the subject of the token (should ideally be the username)
                .setIssuedAt(new Date()) // Sets the issue time of the token
                .setExpiration(new Date(System.currentTimeMillis() + 86400000)) // Sets expiration time (24 hours from creation)
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY) // Signs the token using HMAC SHA-256 and the secret key
                .compact(); // Builds and returns the compact JWT as a String
    }
}
