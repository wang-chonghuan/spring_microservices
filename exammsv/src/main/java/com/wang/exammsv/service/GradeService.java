package com.wang.exammsv.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wang.exammsv.dto.AnswerPaper;
import com.wang.exammsv.dto.BonusDTO;
import com.wang.exammsv.repository.QuestionRepository;
import com.wang.exammsv.repository.StudentExamResultRepository;
import com.wang.exammsv.service.command.GradeCommandChain;
import com.wang.exammsv.service.decorator.QuestionAnswerDecorator;
import com.wang.exammsv.service.interpreter.BonusCalculator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class GradeService {
    @Autowired
    private GradeCommandChain gradeCommandChain;
    @Autowired
    private StudentExamResultRepository resultRepository;
    @Autowired
    private QuestionRepository questionRepository;

    public void broadcastScores(long examId) {
        gradeCommandChain.broadcastScoreProcess().executeCommands(examId);
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
