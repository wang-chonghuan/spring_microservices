package com.wang.exammsv.strategy;

import com.wang.exammsv.domain.Question;

public class StrategyFactory {

    public static Strategy create(Question.QuestionType qtype) {
        switch (qtype) {
            case SINGLE -> {
                return new SingleChoiceStrategy();
            }
            case MULTIPLE -> {
                return new MultipleChoiceStrategy();
            }
            case WRITING -> {
                return new WritingStrategy();
            }
        }
        return new SingleChoiceStrategy();
    }

}
