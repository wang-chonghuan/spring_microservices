package com.wang.studentmsrv.domain.dto;

import lombok.Value;

import java.util.List;

@Value
public class QuestionWithAnswerListDTO {
    long examId;
    long studentId;
    List<Long> questionIdList;
    List<String> answerList;
}
