package com.wang.mgtmsv.mq.event;

import lombok.Value;

@Value
public class BlankpaperEvent {
    long examId;
    String blankpaperContent;
}
