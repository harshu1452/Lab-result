package turing.example.labresult.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

/**
 * Entity class representing a lab result in the database.
 */
@Entity // Marks this class as a JPA entity
@Data // Lombok annotation to generate getters, setters, toString, equals, and hashCode methods
@NoArgsConstructor // Generates a no-argument constructor

@Builder // Enables the Builder pattern for object creation
public class LabResult {

    /**
     * Unique identifier for each lab result.
     */
    @Id // Marks this field as the primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Specifies auto-increment strategy for ID
    private Long id;

    /**
     * Stores the name of the patient associated with the lab result.
     */
    private String patientName;

    /**
     * Stores the name of the test conducted.
     */
    private String testName;

    /**
     * Represents the status of the lab result (e.g., PENDING, COMPLETED).
     */
    @Enumerated(EnumType.STRING) // Maps the enum to a string column in the database
    private ResultStatus status;

    /**
     * Timestamp indicating when the lab result was created.
     */
    private LocalDateTime createdAt;

    /**
     * Stores the ID of the doctor associated with the lab result.
     */
    private Long doctorId;

    /**
     * Constructor with parameters to initialize the lab result.
     *
     * @param id          the unique identifier for the lab result
     * @param patientName the name of the patient
     * @param testName    the name of the test conducted
     * @param status      the status of the lab result
     * @param createdAt   the timestamp when the lab result was created
     * @param doctorId    the associated doctor's ID
     */
    public LabResult(Long id, String patientName, String testName, ResultStatus status, LocalDateTime createdAt, Long doctorId) {
        this.id = id;
        this.patientName = patientName;
        this.testName = testName;
        this.status = status;
        this.createdAt = createdAt;
        this.doctorId = doctorId;
    }

    // Second constructor initializes fields correctly
    public LabResult(long id, String patientName, String testName, ResultStatus status, LocalDateTime createdAt) {
        this.id = id;
        this.patientName = patientName;
        this.testName = testName;
        this.status = status;
        this.createdAt = createdAt;
        this.doctorId = null;  // Assign a default value if doctorId is not provided
    }
}
