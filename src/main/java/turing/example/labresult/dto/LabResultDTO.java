package turing.example.labresult.dto;

import lombok.*;
import turing.example.labresult.entity.ResultStatus;

import java.time.LocalDateTime;

/**
 * Data Transfer Object (DTO) for lab results.
 * Represents the data structure used to transfer lab result information between layers.
 */
@Data // Lombok annotation to generate getters, setters, toString, equals, and hashCode methods
@NoArgsConstructor // Generates a no-argument constructor
@AllArgsConstructor // Generates a constructor with all arguments
@Builder // Enables the Builder pattern for object creation
public class LabResultDTO {

    /** The name of the patient associated with the lab result. */
    private String patientName;

    /** The name of the test conducted. */
    private String testName;

    /** The status of the lab result (e.g., pending, completed). */
    private String status;

    /** The timestamp indicating when the lab result was created. */
    private LocalDateTime createdAt;

    public LabResultDTO(long id, String patientName, String testName, ResultStatus status, LocalDateTime createdAt, long doctorId) {
        this.patientName = patientName;
        this.testName = testName;
        this.status = status.name(); // Convert enum to String
        this.createdAt = createdAt;
    }
}
