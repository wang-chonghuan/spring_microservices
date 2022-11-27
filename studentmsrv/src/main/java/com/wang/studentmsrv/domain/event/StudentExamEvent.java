package com.wang.studentmsrv.domain.event;

import lombok.Data;

import java.util.List;

@Data
public class StudentExamEvent {
    private long examId;
    private List<Long> studentIdList;
}
