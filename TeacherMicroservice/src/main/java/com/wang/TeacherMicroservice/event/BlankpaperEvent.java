package com.wang.TeacherMicroservice.event;

import lombok.Data;
import lombok.Value;

@Data
public class BlankpaperEvent {
    private long examId;
    private String blankpaperContent;
}
