package com.wang.exammsv.service.question;

import com.wang.exammsv.domain.Question;
import com.wang.exammsv.domain.dto.QuestionAnswer;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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
