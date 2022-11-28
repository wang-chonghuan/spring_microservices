package com.wang.studentmsrv.mq;

import com.wang.studentmsrv.domain.StudentExamResult;
import com.wang.studentmsrv.mq.event.StudentExamEvent;
import com.wang.studentmsrv.repository.StudentExamResultRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class QueuesListener {
    @Autowired
    private StudentExamResultRepository studentExamResultRepository;

    // when @RabbitListener is on class level, use @RabbitHandler to annotate the method
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
