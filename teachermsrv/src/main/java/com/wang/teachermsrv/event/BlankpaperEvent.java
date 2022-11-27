package com.wang.teachermsrv.event;

import lombok.Value;

@Value
public class BlankpaperEvent {
    long examId;
    String blankpaperContent;
}
