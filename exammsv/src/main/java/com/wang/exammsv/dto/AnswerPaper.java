package com.wang.exammsv.dto;

import lombok.Data;

import java.util.List;

@Data
public class AnswerPaper {
    public long studentId;
    public long examId;
    List<QuestionAnswer> answerList;
}
