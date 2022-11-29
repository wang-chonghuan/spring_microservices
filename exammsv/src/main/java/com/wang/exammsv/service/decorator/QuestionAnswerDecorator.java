package com.wang.exammsv.service.decorator;

import com.wang.exammsv.domain.Question;
import com.wang.exammsv.dto.QuestionAnswer;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class QuestionAnswerDecorator extends QuestionDecorator {
    private QuestionAnswer qa;

    public QuestionAnswerDecorator(Question q, QuestionAnswer qa) {
        super(q);
        this.qa = qa;
    }

    // this decorator is only for grading
    public double autoGrade() {
        GradeStrategy.Strategy strategy = GradeStrategy.create(super.question.getQuestionType());
        qa.score = strategy.grade(qa.answer, question.getRefAnswer(), qa.mark);
        return qa.score;
    }
}
