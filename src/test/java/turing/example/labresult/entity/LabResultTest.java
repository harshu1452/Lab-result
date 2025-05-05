package turing.example.labresult.entity;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for {@link LabResult}.
 * Ensures correct functionality of constructors, setters, getters, equals, hashCode, and toString methods.
 */
class LabResultTest {

    /**
     * Tests the no-args constructor of {@link LabResult}.
     * Ensures an instance can be created without parameters.
     */
    @Test
    void testNoArgsConstructor() {
        LabResult labResult = new LabResult();
        assertNotNull(labResult);
    }

    /**
     * Tests the all-args constructor of {@link LabResult}.
     * Ensures all values are correctly assigned when using this constructor.
     */
    @Test
    void testAllArgsConstructor() {
        LocalDateTime now = LocalDateTime.now();
        LabResult labResult = new LabResult(1L, "John Doe", "Blood Test", ResultStatus.COMPLETED, now, 101L);

        assertEquals(1L, labResult.getId());
        assertEquals("John Doe", labResult.getPatientName());
        assertEquals("Blood Test", labResult.getTestName());
        assertEquals(ResultStatus.COMPLETED, labResult.getStatus());
        assertEquals(now, labResult.getCreatedAt());
        assertEquals(101L, labResult.getDoctorId());
    }

    /**
     * Tests the builder pattern implementation of {@link LabResult}.
     * Ensures the object is correctly built using the builder.
     */
    @Test
    void testBuilderPattern() {
        LocalDateTime now = LocalDateTime.now();
        LabResult labResult = LabResult.builder()
                .id(2L)
                .patientName("Alice")
                .testName("MRI Scan")
                .status(ResultStatus.PENDING)
                .createdAt(now)
                .build();

        assertEquals(2L, labResult.getId());
        assertEquals("Alice", labResult.getPatientName());
        assertEquals("MRI Scan", labResult.getTestName());
        assertEquals(ResultStatus.PENDING, labResult.getStatus());
        assertEquals(now, labResult.getCreatedAt());
    }

    /**
     * Tests setter and getter methods of {@link LabResult}.
     * Ensures values are correctly assigned and retrieved.
     */
    @Test
    void testSettersAndGetters() {
        LocalDateTime now = LocalDateTime.now();
        LabResult labResult = new LabResult();
        labResult.setId(3L);
        labResult.setPatientName("Bob");
        labResult.setTestName("ECG");
        labResult.setStatus(ResultStatus.APPROVED);
        labResult.setCreatedAt(now);

        assertEquals(3L, labResult.getId());
        assertEquals("Bob", labResult.getPatientName());
        assertEquals("ECG", labResult.getTestName());
        assertEquals(ResultStatus.APPROVED, labResult.getStatus());
        assertEquals(now, labResult.getCreatedAt());
    }

    /**
     * Tests the equals and hashCode methods of {@link LabResult}.
     * Ensures correct equality comparison and hash code generation.
     */
    @Test
    void testEqualsAndHashCode() {
        LocalDateTime now = LocalDateTime.now();
        LabResult result1 = new LabResult(4L, "Charlie", "CT Scan", ResultStatus.COMPLETED, now);
        LabResult result2 = new LabResult(4L, "Charlie", "CT Scan", ResultStatus.COMPLETED, now);
        LabResult result3 = new LabResult(5L, "David", "Ultrasound", ResultStatus.PENDING, now);

        assertEquals(result1, result2);
        assertNotEquals(result1, result3);
        assertEquals(result1.hashCode(), result2.hashCode());
        assertNotEquals(result1.hashCode(), result3.hashCode());
    }

    /**
     * Tests the toString method of {@link LabResult}.
     * Ensures the generated string representation is correct.
     */
    @Test
    void testToString() {
        LocalDateTime now = LocalDateTime.now();
        LabResult labResult = new LabResult(6L, "Eve", "PET Scan", ResultStatus.PROCESSING, now, null); // Include doctorId

        String expected = "LabResult(id=6, patientName=Eve, testName=PET Scan, status=PROCESSING, createdAt=" + now + ", doctorId=null)"; // Include doctorId
        assertEquals(expected, labResult.toString());
    }

}
