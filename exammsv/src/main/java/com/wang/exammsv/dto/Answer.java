package com.wang.exammsv.dto;

import lombok.Value;

@Value
public class Answer {
    long questionId;
    int order;
    double mark;
    String answer;
}
