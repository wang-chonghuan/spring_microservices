package com.wang.mgtmsv.mq.event;

import lombok.Data;

import java.util.List;

@Data
public class ScoreEvent {
    private List<Score> scoreList;
}
