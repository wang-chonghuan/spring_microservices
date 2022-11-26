package com.wang.TeacherMicroservice.event;

import lombok.Data;
import lombok.Value;

@Value
public class BlankpaperEvent {
    long examId;
    String blankpaperContent;
}
