package org.bozdgn.reportservice.messaging;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;
import org.bozdgn.reportservice.dto.messaging.PropertyMessage;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MessageReceiver {
    @RabbitListener(queues = "${app.report-queue}")
    public void receive(@Payload String payload) {
        try {
            ObjectMapper json = new ObjectMapper();
            json.registerModule(new JavaTimeModule());
            PropertyMessage propertyMessage = json.readValue(payload, PropertyMessage.class);
            log.info(propertyMessage.toString());
        } catch (JsonProcessingException e) {
            log.error("Error on deserializing to JSON: {}", e.getMessage());
        }
    }
}
