package turing.example.labresult.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Configuration class for Spring Security settings.
 * This class defines the security configurations, including authorization rules and session management.
 */
@Configuration
public class SecurityConfig {

    /**
     * Defines the security filter chain for the application.
     *
     * @param http the {@link HttpSecurity} instance used to configure security settings
     * @return the configured {@link SecurityFilterChain}
     * @throws Exception if an error occurs during security configuration
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                /**
                 * Disables CSRF (Cross-Site Request Forgery) protection.
                 * Since this application is stateless (JWT-based authentication), CSRF protection is not needed.
                 */
                .csrf().disable()

                .authorizeHttpRequests(auth -> auth
                        /**
                         * Allows unauthenticated access to Swagger UI and API documentation endpoints.
                         * These endpoints are used for API testing and documentation, so they remain publicly accessible.
                         */
                        .requestMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll()

                        /**
                         * Requires authentication for all other API requests.
                         * Any request not explicitly permitted above must be authenticated.
                         */
                        .anyRequest().authenticated()
                )

                /**
                 * Configures session management as stateless.
                 * Since JWT authentication is being used, sessions are not needed, and each request must be independently authenticated.
                 */
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        /**
         * Builds and returns the security filter chain.
         */
        return http.build();
    }
}
