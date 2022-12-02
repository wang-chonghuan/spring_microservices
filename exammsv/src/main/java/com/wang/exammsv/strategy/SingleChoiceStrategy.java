package com.wang.exammsv.strategy;

class SingleChoiceStrategy implements Strategy {

    @Override
    public double grade(String answer, String refAnswer, double mark) {
        if (answer.equals(refAnswer)) {
            return mark;
        } else {
            return 0.0;
        }
    }
}
