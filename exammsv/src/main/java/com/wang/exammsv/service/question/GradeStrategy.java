package com.wang.exammsv.service.question;

import com.wang.exammsv.domain.Question;

import java.util.Arrays;
import java.util.List;

public class GradeStrategy {

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
        return null;
    }

    public interface Strategy {
        double grade(String answer, String refAnswer, double mark);
    }

    // NOTICE! this class must be static, otherwise it cant be produced by the factory
    private static class SingleChoiceStrategy implements Strategy {

        @Override
        public double grade(String answer, String refAnswer, double mark) {
            if(answer.equals(refAnswer)) {
                return mark;
            } else {
                return 0.0;
            }
        }
    }

    private static class MultipleChoiceStrategy implements Strategy {

        @Override
        public double grade(String answer, String refAnswer, double mark) {
            String[] answerArray = answer.split(",");
            List<String> refList = Arrays.asList(refAnswer.split(","));
            double eachMark = mark / Double.valueOf(refList.size());
            double finalScore = 0.0;
            for(String eachAnswer : answerArray) {
                if(refList.contains(eachAnswer)) { // one right, accumulate one choice's mark
                    finalScore += eachMark;
                } else { // one wrong, final score is 0
                    finalScore = 0.0;
                    break;
                }
            }
            return finalScore;
        }
    }

    private static class WritingStrategy implements Strategy {

        @Override
        public double grade(String answer, String refAnswer, double mark) {
            return 0.0;
        }
    }
}
