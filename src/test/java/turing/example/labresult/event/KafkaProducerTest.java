package turing.example.labresult.event;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.kafka.core.KafkaTemplate;

/**
 * Unit tests for {@link KafkaProducer}.
 * Ensures that Kafka messages are sent correctly.
 */
class KafkaProducerTest {

    @Mock
    private KafkaTemplate<String, LabResultEvent> kafkaTemplate;

    @InjectMocks
    private KafkaProducer producer;

    /**
     * Initializes mocks before each test execution.
     */
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Tests whether the {@code sendLabResultEvent} method correctly sends a Kafka message.
     */
    @Test
    void sendLabResultEvent_ShouldSendMessage() {
        // Arrange
        LabResultEvent event = new LabResultEvent("Bob", "MRI", "PENDING");

        // Act
        producer.sendLabResultEvent(event);

        // Assert
        verify(kafkaTemplate, times(1)).send(eq("lab-results-topic"), eq(event));
    }
}
