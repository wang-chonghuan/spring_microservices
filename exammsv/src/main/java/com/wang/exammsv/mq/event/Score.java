package com.wang.exammsv.mq.event;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Score {
    private long examId;
    private long studentId;
    private double score;
}
