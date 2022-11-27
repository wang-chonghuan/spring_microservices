package com.wang.teachermsrv.service.publisher;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class BlankpaperPublisher extends AbstractPublisher {
    public BlankpaperPublisher(final AmqpTemplate amqpTemplate,
                             @Value("${amqp.exchange.teacher}") final String topicExchange,
                             @Value("${amqp.routingkey.blankpaper}") final String routingKey) {
        super(amqpTemplate, topicExchange, routingKey);
    }
}
