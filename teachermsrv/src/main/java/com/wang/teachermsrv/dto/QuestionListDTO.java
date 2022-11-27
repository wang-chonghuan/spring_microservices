package com.wang.teachermsrv.dto;

import lombok.Value;

import java.util.List;

@Value
public class QuestionListDTO {
    long examId;
    List<Long> questionIdList;
}
