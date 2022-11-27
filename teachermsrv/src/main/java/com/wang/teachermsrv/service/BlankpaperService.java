package com.wang.teachermsrv.service;

import com.wang.teachermsrv.dto.QuestionListDTO;
import com.wang.teachermsrv.event.BlankpaperEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BlankpaperService {
    private final BlankpaperPublisher blankpaperPublisher;
    public void createBlankpaper(QuestionListDTO questionListDTO) {
        BlankpaperEvent event = new BlankpaperEvent(
                questionListDTO.getExamId(),
                questionListDTO
                        .getQuestionIdList()
                        .stream()
                        .map(String::valueOf)
                        .collect(Collectors.joining(","))
        );
        blankpaperPublisher.publish(event);
    }
}