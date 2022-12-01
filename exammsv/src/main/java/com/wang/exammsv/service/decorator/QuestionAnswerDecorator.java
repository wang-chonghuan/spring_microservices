package com.wang.exammsv.service.decorator;

import com.wang.exammsv.domain.Question;
import com.wang.exammsv.dto.Answer;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class QuestionAnswerDecorator extends QuestionDecorator {
    private Answer qa;

    public QuestionAnswerDecorator(Question q, Answer qa) {
        super(q);
        this.qa = qa;
    }

    // this decorator is only for grading
    public double autoGrade() {
        GradeStrategy.Strategy strategy = GradeStrategy.create(super.question.getQuestionType());
        var score = strategy.grade(qa.getAnswer(), question.getRefAnswer(), qa.getMark());
        return score;
    }
}
