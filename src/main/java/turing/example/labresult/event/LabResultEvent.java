package turing.example.labresult.event;

import lombok.*;

/**
 * Event class representing a lab result, used for messaging through Kafka.
 */
@Data // Lombok annotation to generate getters, setters, toString, equals, and hashCode methods
@NoArgsConstructor // Generates a no-argument constructor
@AllArgsConstructor // Generates a constructor with all arguments
@Builder // Enables the Builder pattern for object creation
public class LabResultEvent {

    /**
     * Unique identifier for the lab result event.
     */
    private Long id;

    /**
     * Name of the patient associated with the lab result.
     */
    private String patientName;

    /**
     * Name of the lab test performed.
     */
    private String testName;

    /**
     * Status of the lab result (e.g., pending, completed, approved).
     */
    private String status;

    /**
     * Custom constructor to initialize specific fields (excluding ID).
     *
     * @param patientName Name of the patient.
     * @param testName Name of the lab test.
     * @param status Status of the lab result.
     */
    public LabResultEvent(String patientName, String testName, String status) {
        this.patientName = patientName;
        this.testName = testName;
        this.status = status;
    }
}
