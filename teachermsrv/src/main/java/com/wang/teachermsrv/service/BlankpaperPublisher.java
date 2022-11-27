package com.wang.teachermsrv.service;

import com.wang.teachermsrv.event.BlankpaperEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class BlankpaperPublisher {
    private final AmqpTemplate amqpTemplate;
    private final String blankPaperTopicExchange;
    public BlankpaperPublisher(final AmqpTemplate amqpTemplate,
                               @Value("${amqp.exchange.blankpaper}") final String blankPaperTopicExchange) {
        this.amqpTemplate = amqpTemplate;
        this.blankPaperTopicExchange = blankPaperTopicExchange;
    }

    public void publish(final BlankpaperEvent event) {
        String routingKey = "blankpaper";
        amqpTemplate.convertAndSend(blankPaperTopicExchange, routingKey, event);
    }
}
