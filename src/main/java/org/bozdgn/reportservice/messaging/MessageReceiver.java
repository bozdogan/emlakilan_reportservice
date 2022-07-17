package org.bozdgn.reportservice.messaging;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;
import org.bozdgn.reportservice.dto.messaging.PropertyMessage;
import org.bozdgn.reportservice.service.ReportService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MessageReceiver {
    private final ReportService reportService;

    public MessageReceiver(ReportService reportService) {
        this.reportService = reportService;
    }

    @RabbitListener(queues = "${app.report-queue}")
    public void receive(@Payload String payload) {
        try {
            ObjectMapper json = new ObjectMapper();
            json.registerModule(new JavaTimeModule());
            PropertyMessage propertyMessage = json.readValue(payload, PropertyMessage.class);

            if (isPresent(propertyMessage)) {
                log.info("Received {}", propertyMessage.toString());
                reportService.save(propertyMessage);
            } else {
                log.warn("Empty object received {}", propertyMessage.toString());
            }

        } catch (JsonProcessingException e) {
            log.error("Error on deserializing to JSON: {}", e.getMessage());
        }
    }

    private boolean isPresent(PropertyMessage propertyMessage) {
        return propertyMessage.getPropertyID() != null
                && propertyMessage.getAuthor() != null
                && propertyMessage.getDateCreated() != null
                && propertyMessage.getViewCount() != null;
    }
}
