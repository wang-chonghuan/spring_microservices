package com.wang.exammsv.dto;

import lombok.Value;

import java.util.List;

@Value
public class AnswersDTO {
    long examId;
    long studentId;
    List<Answer> answerList;
}
