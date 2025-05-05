package turing.example.labresult;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main class for the Lab Results API application.
 * This class serves as the entry point for the Spring Boot application.
 */
@SpringBootApplication // Marks this as the main Spring Boot application
public class LabResultsApiApplication {

    /**
     * Main method to launch the Spring Boot application.
     *
     * @param args Command-line arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(LabResultsApiApplication.class, args); // Starts the Spring Boot application
    }
}
