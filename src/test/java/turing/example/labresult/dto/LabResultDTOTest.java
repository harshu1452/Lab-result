package turing.example.labresult.dto;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for {@link LabResultDTO}.
 * Ensures proper functioning of constructors, getters, setters, equals, hashCode, and toString methods.
 */
class LabResultDTOTest {

    /**
     * Tests the getter and setter methods of {@link LabResultDTO}.
     * Ensures values are properly set and retrieved.
     */
    @Test
    void testLabResultDTOGettersAndSetters() {
        LocalDateTime now = LocalDateTime.now();
        LabResultDTO dto = new LabResultDTO();
        dto.setPatientName("John Doe");
        dto.setTestName("Blood Test");
        dto.setStatus("Pending");
        dto.setCreatedAt(now);

        assertEquals("John Doe", dto.getPatientName());
        assertEquals("Blood Test", dto.getTestName());
        assertEquals("Pending", dto.getStatus());
        assertEquals(now, dto.getCreatedAt());
    }

    /**
     * Tests the all-args constructor of {@link LabResultDTO}.
     * Ensures all values are correctly assigned when using this constructor.
     */
    @Test
    void testLabResultDTOAllArgsConstructor() {
        LocalDateTime now = LocalDateTime.now();
        LabResultDTO dto = new LabResultDTO("Jane Doe", "X-Ray", "Completed", now);

        assertEquals("Jane Doe", dto.getPatientName());
        assertEquals("X-Ray", dto.getTestName());
        assertEquals("Completed", dto.getStatus());
        assertEquals(now, dto.getCreatedAt());
    }

    /**
     * Tests the builder pattern implementation of {@link LabResultDTO}.
     * Ensures the object is correctly built using the builder.
     */
    @Test
    void testLabResultDTOBuilder() {
        LocalDateTime now = LocalDateTime.now();
        LabResultDTO dto = LabResultDTO.builder()
                .patientName("Alice")
                .testName("MRI Scan")
                .status("In Progress")
                .createdAt(now)
                .build();

        assertEquals("Alice", dto.getPatientName());
        assertEquals("MRI Scan", dto.getTestName());
        assertEquals("In Progress", dto.getStatus());
        assertEquals(now, dto.getCreatedAt());
    }

    /**
     * Tests the equals and hashCode methods of {@link LabResultDTO}.
     * Ensures correct equality comparison and hash code generation.
     */
    @Test
    void testLabResultDTOEqualsAndHashCode() {
        LocalDateTime now = LocalDateTime.now();
        LabResultDTO dto1 = new LabResultDTO("Bob", "ECG", "Pending", now);
        LabResultDTO dto2 = new LabResultDTO("Bob", "ECG", "Pending", now);
        LabResultDTO dto3 = new LabResultDTO("Charlie", "ECG", "Completed", now);

        assertEquals(dto1, dto2);
        assertNotEquals(dto1, dto3);
        assertEquals(dto1.hashCode(), dto2.hashCode());
        assertNotEquals(dto1.hashCode(), dto3.hashCode());
    }

    /**
     * Tests the toString method of {@link LabResultDTO}.
     * Ensures the generated string representation is correct.
     */
    @Test
    void testLabResultDTOToString() {
        LocalDateTime now = LocalDateTime.now();
        LabResultDTO dto = new LabResultDTO("David", "CT Scan", "Approved", now);

        String expectedString = "LabResultDTO(patientName=David, testName=CT Scan, status=Approved, createdAt=" + now + ")";
        assertEquals(expectedString, dto.toString());
    }
}
