package com.wang.studentmsrv.service.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.wang.studentmsrv.domain.Blankpaper;
import com.wang.studentmsrv.domain.event.BlankpaperEvent;
import com.wang.studentmsrv.repository.BlankpaperRepository;
import com.wang.studentmsrv.utils.AnyUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class BlankpaperEventHandler {

    @Autowired
    private BlankpaperRepository blankpaperRepository;

    @RabbitListener(queues="${amqp.queue.blankpaper}")
    void handleBlankpaperEvant(final BlankpaperEvent event) {
        try {
            log.info("handleBlankpaperEvant, examId {}, content {}", event.getExamId(), event.getBlankpaperContent());
            Blankpaper b = new Blankpaper(
                    event.getExamId(), AnyUtil.stringToJsonmap(event.getBlankpaperContent()));
            blankpaperRepository.save(b);
        } catch (final Exception e) {
            log.error("error when trying to process challengeSovedEvent", e);
            throw new AmqpRejectAndDontRequeueException(e);
        }
    }

}
