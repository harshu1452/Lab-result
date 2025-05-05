package turing.example.labresult.event;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for {@link LabResultEvent}.
 * Ensures correct behavior of constructors, builder pattern, getters, setters, and utility methods.
 */
class LabResultEventTest {

    /**
     * Tests if the no-args constructor correctly initializes an empty object.
     */
    @Test
    void testNoArgsConstructor() {
        LabResultEvent event = new LabResultEvent();
        assertNotNull(event);
    }

    /**
     * Tests if the all-args constructor correctly initializes an object with provided values.
     */
    @Test
    void testAllArgsConstructor() {
        LabResultEvent event = new LabResultEvent(1L, "John Doe", "Blood Test", "Completed");

        assertEquals(1L, event.getId());
        assertEquals("John Doe", event.getPatientName());
        assertEquals("Blood Test", event.getTestName());
        assertEquals("Completed", event.getStatus());
    }

    /**
     * Tests if the constructor without an ID initializes fields correctly.
     */
    @Test
    void testPartialConstructor() {
        LabResultEvent event = new LabResultEvent("Jane Doe", "X-Ray", "Pending");

        assertNull(event.getId());
        assertEquals("Jane Doe", event.getPatientName());
        assertEquals("X-Ray", event.getTestName());
        assertEquals("Pending", event.getStatus());
    }

    /**
     * Tests if the builder pattern correctly constructs an object with the given values.
     */
    @Test
    void testBuilderPattern() {
        LabResultEvent event = LabResultEvent.builder()
                .id(2L)
                .patientName("Alice")
                .testName("MRI Scan")
                .status("In Progress")
                .build();

        assertEquals(2L, event.getId());
        assertEquals("Alice", event.getPatientName());
        assertEquals("MRI Scan", event.getTestName());
        assertEquals("In Progress", event.getStatus());
    }

    /**
     * Tests if setters and getters work correctly.
     */
    @Test
    void testSettersAndGetters() {
        LabResultEvent event = new LabResultEvent();
        event.setId(3L);
        event.setPatientName("Bob");
        event.setTestName("ECG");
        event.setStatus("Approved");

        assertEquals(3L, event.getId());
        assertEquals("Bob", event.getPatientName());
        assertEquals("ECG", event.getTestName());
        assertEquals("Approved", event.getStatus());
    }

    /**
     * Tests if equals and hashCode methods work correctly.
     */
    @Test
    void testEqualsAndHashCode() {
        LabResultEvent event1 = new LabResultEvent(4L, "Charlie", "CT Scan", "Completed");
        LabResultEvent event2 = new LabResultEvent(4L, "Charlie", "CT Scan", "Completed");
        LabResultEvent event3 = new LabResultEvent(5L, "David", "Ultrasound", "Pending");

        assertEquals(event1, event2);
        assertNotEquals(event1, event3);
        assertEquals(event1.hashCode(), event2.hashCode());
        assertNotEquals(event1.hashCode(), event3.hashCode());
    }

    /**
     * Tests if the toString method returns the expected formatted string.
     */
    @Test
    void testToString() {
        LabResultEvent event = new LabResultEvent(6L, "Eve", "PET Scan", "Processing");

        String expected = "LabResultEvent(id=6, patientName=Eve, testName=PET Scan, status=Processing)";
        assertEquals(expected, event.toString());
    }
}
