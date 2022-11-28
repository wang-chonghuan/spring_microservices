package com.wang.exammsv.domain.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Value;

@Getter
public class QuestionAnswer {
    public long questionId;
    public int order;
    public double mark;
    public double score;
    public String answer;
}
