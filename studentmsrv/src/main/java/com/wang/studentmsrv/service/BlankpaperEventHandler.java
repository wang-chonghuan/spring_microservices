package com.wang.studentmsrv.service;

import com.wang.studentmsrv.event.BlankpaperEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class BlankpaperEventHandler {


    @RabbitListener(queues="${amqp.queue.blankpaper}")
    void handleBlankpaperEvant(final BlankpaperEvent event) {
        log.info("handleBlankpaperEvant, examId {}, content {}", event.getExamId(), event.getBlankpaperContent());
        try {
        } catch (final Exception e) {
            log.error("error when trying to process challengeSovedEvent", e);
            throw new AmqpRejectAndDontRequeueException(e);
        }
    }

}
