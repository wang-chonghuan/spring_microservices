package com.wang.exammsv.strategy;

public interface Strategy {
    double grade(String answer, String refAnswer, double mark);
}
