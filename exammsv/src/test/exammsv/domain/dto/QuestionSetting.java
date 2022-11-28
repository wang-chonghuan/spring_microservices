package com.wang.exammsv.domain.dto;

import lombok.Value;

@Value
public class QuestionSetting {
    public long questionId;
    public int order;
    public double mark;
}
