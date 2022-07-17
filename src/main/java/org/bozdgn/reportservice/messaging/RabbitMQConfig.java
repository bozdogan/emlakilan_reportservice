package org.bozdgn.reportservice.messaging;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    @Value("${app.report-queue}")
    private String queueName;
    @Value("${app.property-queue}")
    private String propertyQueueName;

    @Bean
    public Queue queue() {
        return new Queue(queueName, true);
    }

    @Bean
    public Queue propertyQueue() {
        return new Queue(queueName, true);
    }
}
