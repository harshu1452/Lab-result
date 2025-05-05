package turing.example.labresult.entity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for {@link ResultStatus} enum.
 * Ensures correct functionality of enum value retrieval and string representation.
 */
class ResultStatusTest {

    /**
     * Tests if enum values can be correctly retrieved using {@code valueOf()}.
     * Ensures that each string representation correctly maps to the corresponding enum constant.
     */
    @Test
    void testEnumValues() {
        assertEquals(ResultStatus.PENDING, ResultStatus.valueOf("PENDING"));
        assertEquals(ResultStatus.COMPLETED, ResultStatus.valueOf("COMPLETED"));
        assertEquals(ResultStatus.REJECTED, ResultStatus.valueOf("REJECTED"));
    }

    /**
     * Tests the {@code toString()} method of {@link ResultStatus}.
     * Ensures that each enum constant returns the correct string representation.
     */
    @Test
    void testEnumToString() {
        assertEquals("PENDING", ResultStatus.PENDING.toString());
        assertEquals("COMPLETED", ResultStatus.COMPLETED.toString());
        assertEquals("REJECTED", ResultStatus.REJECTED.toString());
    }
}
