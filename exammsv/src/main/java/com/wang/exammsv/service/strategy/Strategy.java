package com.wang.exammsv.service.strategy;

public interface Strategy {
    double grade(String answer, String refAnswer, double mark);
}
