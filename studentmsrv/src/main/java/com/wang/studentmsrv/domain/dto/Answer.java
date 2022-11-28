package com.wang.studentmsrv.domain.dto;

import lombok.Value;

@Value
public class Answer {
    long questionId;
    int order;
    double mark;
    String answer;
}
