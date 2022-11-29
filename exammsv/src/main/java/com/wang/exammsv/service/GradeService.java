package com.wang.exammsv.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wang.exammsv.domain.StudentExamResult;
import com.wang.exammsv.dto.AnswerPaper;
import com.wang.exammsv.dto.BonusDTO;
import com.wang.exammsv.mq.ScorePublisher;
import com.wang.exammsv.mq.event.Score;
import com.wang.exammsv.mq.event.ScoreEvent;
import com.wang.exammsv.repository.QuestionRepository;
import com.wang.exammsv.repository.StudentExamResultRepository;
import com.wang.exammsv.service.decorator.QuestionAnswerDecorator;
import com.wang.exammsv.service.interpreter.BonusCalculator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class GradeService {
    @Autowired
    private ScorePublisher scorePublisher;

    @Autowired
    private StudentExamResultRepository resultRepository;
    @Autowired
    private QuestionRepository questionRepository;

    public void broadcastScores(long examId) {
        List<StudentExamResult> resultList = resultRepository.findByExamId(examId);
        scorePublisher.publish(new ScoreEvent(resultList.stream()
                        .map(r -> new Score(r.getExamId(), r.getStudentId(), r.getScore()))
                        .collect(Collectors.toList())));
    }

    public void autoGrade(long examId) throws JsonProcessingException {
        var resultList = resultRepository.findByExamId(examId);
        for(var result : resultList) {
            AnswerPaper answerPaper = new ObjectMapper().convertValue(result.getAnsweredpaper(), AnswerPaper.class);
            if(answerPaper.getAnswerList() == null) {
                continue;
            }
            for(var answer : answerPaper.getAnswerList()) {
                var q = questionRepository.findById(answer.getQuestionId());
                var qad = new QuestionAnswerDecorator(q.get(), answer);
                var score = qad.autoGrade();
                log.info("score {} examId {} studentId {} questionId {}", score, examId, result.getStudentId(), answer.getQuestionId());
            }
        }
    }

    public void addBonus(BonusDTO bonusDTO) {
        var resultList = resultRepository.findByExamId(bonusDTO.getExamId());
        for(var result : resultList) {
            var oriScore = result.getScore();
            var newScore = BonusCalculator.interpret(bonusDTO.getExpression(), oriScore);
            result.setScore(newScore);
            resultRepository.save(result);
        }
    }
}
