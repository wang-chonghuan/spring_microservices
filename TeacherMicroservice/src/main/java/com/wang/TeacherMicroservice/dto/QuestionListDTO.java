package com.wang.TeacherMicroservice.dto;

import lombok.Data;
import lombok.Value;

import java.util.List;

@Value
public class QuestionListDTO {
    long examId;
    List<Long> questionIdList;
}
