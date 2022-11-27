package com.wang.studentmsrv.service.listener;

import com.wang.studentmsrv.domain.Blankpaper;
import com.wang.studentmsrv.domain.StudentExamResult;
import com.wang.studentmsrv.domain.event.BlankpaperEvent;
import com.wang.studentmsrv.domain.event.StudentExamEvent;
import com.wang.studentmsrv.repository.BlankpaperRepository;
import com.wang.studentmsrv.repository.StudentExamResultRepository;
import com.wang.studentmsrv.utils.AnyUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class QueuesListener {

    @Autowired
    private BlankpaperRepository blankpaperRepository;
    @Autowired
    private StudentExamResultRepository studentExamResultRepository;

    // when @RabbitListener is on class level, use @RabbitHandler to annotate the method
    @RabbitListener(queues="${amqp.queue.blankpaper}")
    void handleBlankpaperEvent(final BlankpaperEvent event) {
        try {
            log.info("handleBlankpaperEvent, examId {}, content {}", event.getExamId(), event.getBlankpaperContent());
            Blankpaper b = new Blankpaper(
                    event.getExamId(), AnyUtil.stringToJsonmap(event.getBlankpaperContent()));
            blankpaperRepository.save(b);
        } catch (final Exception e) {
            log.error("error when trying to process handleBlankpaperEvent", e);
            throw new AmqpRejectAndDontRequeueException(e);
        }
    }

    @RabbitListener(queues="${amqp.queue.studentexam}")
    void handleStudentExamEvent(final StudentExamEvent event) {
        try {
            log.info("handleStudentExamEvent, examId {}, content {}", event.getExamId(), event.getStudentIdList());
            studentExamResultRepository.saveAll(
                    event.getStudentIdList()
                            .stream()
                            .map(id -> new StudentExamResult(id, event.getExamId()))
                            .collect(Collectors.toList()));
        } catch (final Exception e) {
            log.error("error when trying to process handleStudentExamEvent", e);
            throw new AmqpRejectAndDontRequeueException(e);
        }
    }



}
