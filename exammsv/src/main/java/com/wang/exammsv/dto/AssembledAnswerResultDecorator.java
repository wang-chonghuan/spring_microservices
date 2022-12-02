package com.wang.exammsv.dto;

import com.wang.exammsv.domain.StudentExamResult;
import com.wang.exammsv.strategy.StrategyFactory;
import com.wang.exammsv.strategy.Strategy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

@Slf4j
@RequiredArgsConstructor
public class AssembledAnswerResultDecorator {
    private final StudentExamResult result;
    private AssembledAnswerDTO assembledAnswerDTO; // forbid other class to read/write this DTO

    // dto should only be used here
    public void calculateScore() {
        assembledAnswerDTO.getAssembledAnswerList().forEach(assembledAnswer -> {
            Strategy strategy = StrategyFactory.create(assembledAnswer.getQuestionPOJO().getQuestionType());
            var score = strategy.grade(
                    assembledAnswer.getAnswer().getAnswer(),
                    assembledAnswer.getQuestionPOJO().getRefAnswer(),
                    assembledAnswer.getAnswer().getMark());
            assembledAnswer.setScore(score);
            log.info("score {} examId {} studentId {} questionId {}",
                    score, assembledAnswerDTO.getExamId(), assembledAnswerDTO.getStudentId(),
                    assembledAnswer.getQuestionPOJO().getQuestionId());
        });
        result.setScore(assembledAnswerDTO.getAssembledAnswerList().stream()
                .mapToDouble(AssembledAnswer::getScore).sum());
    }

    public void injectAssembledAnswerDTO(AssembledAnswerDTO assembledAnswerDTO) {
        this.assembledAnswerDTO = assembledAnswerDTO;
    }

    public Map<String, Object> getAnsweredpaper() {
        return result.getAnsweredpaper();
    }
    public void setAssembledpaper(Map<String, Object> assembledpaperJsonmap) { result.setAssembledpaper(assembledpaperJsonmap);}

    public long getStudentId() {return result.getStudentId();}

    public long getExamId() {
        return result.getExamId();
    }

    public double getScore() {
        return result.getScore();
    }

    public void setScore(double score) { result.setScore(score); }
    public StudentExamResult getResult() { return this.result; }
}
