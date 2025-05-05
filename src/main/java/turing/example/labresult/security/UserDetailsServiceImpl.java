package turing.example.labresult.security;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Collections;

/**
 * Implementation of Spring Security's UserDetailsService.
 * This service is responsible for loading user-specific data during authentication.
 */
@Service // Marks this class as a Spring-managed service
public class UserDetailsServiceImpl implements UserDetailsService {

    /**
     * Loads a user by their username.
     *
     * @param username the username of the user to load
     * @return UserDetails object containing the user's details
     */
    @Override
    public UserDetails loadUserByUsername(String username) {
        return new User(username, "{noop}password", Collections.emptyList()); // Creates a dummy user with no roles
    }
}
