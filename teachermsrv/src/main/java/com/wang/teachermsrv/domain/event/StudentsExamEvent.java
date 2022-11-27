package com.wang.teachermsrv.domain.event;

import lombok.Value;

import java.util.List;

@Value
public class StudentsExamEvent {
    long examId;
    List<Long> studentIdList;
}
