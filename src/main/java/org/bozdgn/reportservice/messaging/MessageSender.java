package org.bozdgn.reportservice.messaging;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;
import org.bozdgn.reportservice.dto.messaging.UpdateRequestMessage;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MessageSender {
    private final RabbitTemplate rabbitTemplate;
    private final Queue propertyQueue;

    public MessageSender(RabbitTemplate rabbitTemplate, @Qualifier("propertyQueue") Queue propertyQueue) {
        this.rabbitTemplate = rabbitTemplate;
        this.propertyQueue = propertyQueue;
    }

    public void sendPropertyUpdateRequest(UpdateRequestMessage updateRequest) {
        try {
            ObjectMapper json = new ObjectMapper();
            json.registerModule(new JavaTimeModule());
            String payload = json.writeValueAsString(updateRequest);
            rabbitTemplate.convertAndSend(propertyQueue.getName(), payload);
            log.info("Property info update request sent for Property #" + updateRequest.getPropertyID());
        } catch (JsonProcessingException e) {
            log.error("Error on serializing to JSON: {}", e.getMessage());
        }
    }
}
