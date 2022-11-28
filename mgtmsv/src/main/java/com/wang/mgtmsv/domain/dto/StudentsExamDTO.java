package com.wang.mgtmsv.domain.dto;

import lombok.Value;

import java.util.List;

@Value
public class StudentsExamDTO {
    long examId;
    List<Long> studentIdList;
}
