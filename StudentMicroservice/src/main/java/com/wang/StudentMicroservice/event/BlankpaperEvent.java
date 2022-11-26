package com.wang.StudentMicroservice.event;

import lombok.Data;

@Data
public class BlankpaperEvent {
    private long examId;
    private String blankpaperContent;
}
