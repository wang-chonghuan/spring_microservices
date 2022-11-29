package com.wang.exammsv.dto;

import lombok.Getter;
import lombok.Value;

@Getter
public class QuestionSetting {
    public long questionId;
    public int order;
    public double mark;
}
