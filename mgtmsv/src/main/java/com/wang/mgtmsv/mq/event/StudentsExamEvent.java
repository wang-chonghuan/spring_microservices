package com.wang.mgtmsv.mq.event;

import lombok.Value;

import java.util.List;

@Value
public class StudentsExamEvent {
    long examId;
    List<Long> studentIdList;
}
