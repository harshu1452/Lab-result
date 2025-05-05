package turing.example.labresult.event;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

/**
 * Kafka producer service for publishing lab result events.
 */
@Service // Marks this class as a Spring-managed service component
@RequiredArgsConstructor // Lombok annotation to generate a constructor with required (final) fields
public class KafkaProducer {

    /**
     * KafkaTemplate used for sending messages to Kafka.
     */
    private final KafkaTemplate<String, LabResultEvent> kafkaTemplate; // KafkaTemplate for sending messages to Kafka

    /**
     * Sends a LabResultEvent to the "lab-results-topic" Kafka topic.
     *
     * @param event The LabResultEvent to be sent.
     */
    public void sendLabResultEvent(LabResultEvent event) {
        kafkaTemplate.send("lab-results-topic", event); // Sends the event to the specified Kafka topic
    }
}
