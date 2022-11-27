package com.wang.studentmsrv.domain.event;

import lombok.Data;

@Data
public class BlankpaperEvent {
    private long examId;
    private String blankpaperContent;
}
