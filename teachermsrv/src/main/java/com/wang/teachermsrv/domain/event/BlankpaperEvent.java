package com.wang.teachermsrv.domain.event;

import lombok.Value;

@Value
public class BlankpaperEvent {
    long examId;
    String blankpaperContent;
}
