package com.wang.exammsv.service.strategy;

import java.util.Arrays;
import java.util.List;

class MultipleChoiceStrategy implements Strategy {
    @Override
    public double grade(String answer, String refAnswer, double mark) {
        String[] answerArray = answer.split(",");
        List<String> refList = Arrays.asList(refAnswer.split(","));
        double eachMark = mark / (double) refList.size();
        double finalScore = 0.0;
        for (String eachAnswer : answerArray) {
            if (refList.contains(eachAnswer)) { // one right, accumulate one choice's mark
                finalScore += eachMark;
            } else { // one wrong, final score is 0
                finalScore = 0.0;
                break;
            }
        }
        return finalScore;
    }
}
