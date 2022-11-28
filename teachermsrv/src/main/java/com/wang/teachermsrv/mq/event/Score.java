package com.wang.teachermsrv.mq.event;

import lombok.Data;

@Data
public class Score {
    private long studentId;
    private long examId;
    private double score;
}
