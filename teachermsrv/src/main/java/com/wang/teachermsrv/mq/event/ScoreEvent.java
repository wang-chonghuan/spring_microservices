package com.wang.teachermsrv.mq.event;

import lombok.Data;

import java.util.List;

@Data
public class ScoreEvent {
    private List<Score> scoreList;
}
