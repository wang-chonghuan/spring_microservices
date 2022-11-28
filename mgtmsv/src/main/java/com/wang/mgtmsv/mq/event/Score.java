package com.wang.mgtmsv.mq.event;

import lombok.Data;

@Data
public class Score {
    private long studentId;
    private long examId;
    private double score;
}
