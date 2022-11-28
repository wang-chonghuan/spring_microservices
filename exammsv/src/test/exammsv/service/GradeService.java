package com.wang.exammsv.service;

import com.wang.exammsv.domain.StudentExamResult;
import com.wang.exammsv.mq.ScorePublisher;
import com.wang.exammsv.mq.event.Score;
import com.wang.exammsv.mq.event.ScoreEvent;
import com.wang.exammsv.repository.StudentExamResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GradeService {
    @Autowired
    private ScorePublisher scorePublisher;

    @Autowired
    private StudentExamResultRepository studentExamResultRepository;

    public void broadcastScores(long examId) {
        List<StudentExamResult> resultList = studentExamResultRepository.findByExamId(examId);
        scorePublisher.publish(new ScoreEvent(resultList.stream()
                        .map(r -> new Score(r.getExamId(), r.getStudentId(), r.getScore()))
                        .collect(Collectors.toList())));
    }
}
