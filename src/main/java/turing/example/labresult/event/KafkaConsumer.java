package turing.example.labresult.event;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

/**
 * Kafka consumer service for processing lab result events.
 */
@Service // Marks this class as a Spring-managed service component
public class KafkaConsumer {

    /**
     * Listens to messages from the "lab-results-topic" Kafka topic.
     * Processes incoming LabResultEvent messages.
     *
     * @param event The received LabResultEvent.
     */
    @KafkaListener(topics = "lab-results-topic", groupId = "lab-results-group") // Listens to messages from the specified Kafka topic
    public void consumeLabResultEvent(LabResultEvent event) {
        System.out.println("Received Lab Result Event: " + event); // Logs the received event
    }
}
