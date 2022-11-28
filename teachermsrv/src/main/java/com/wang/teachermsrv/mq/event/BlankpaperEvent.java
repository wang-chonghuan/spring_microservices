package com.wang.teachermsrv.mq.event;

import lombok.Value;

@Value
public class BlankpaperEvent {
    long examId;
    String blankpaperContent;
}
