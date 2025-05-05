package turing.example.labresult.event;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for {@link KafkaConsumer}.
 * Ensures the correct handling of Kafka events.
 */
class KafkaConsumerTest {

    @InjectMocks
    private KafkaConsumer kafkaConsumer;

    /**
     * Initializes mocks before each test execution.
     */
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Tests the {@code consumeLabResultEvent} method to ensure it processes events without exceptions.
     */
    @Test
    void testConsumeLabResultEvent() {
        LabResultEvent event = new LabResultEvent("Test Patient", "Blood Test", "Completed");

        assertDoesNotThrow(() -> kafkaConsumer.consumeLabResultEvent(event));
    }
}
