package com.wang.TeacherMicroservice.service;

import com.wang.TeacherMicroservice.dto.QuestionListDTO;
import com.wang.TeacherMicroservice.event.BlankpaperEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BlankpaperService {
    private final BlankpaperPublisher blankpaperPublisher;
    public void createBlankpaper(QuestionListDTO questionListDTO) {
        BlankpaperEvent event = new BlankpaperEvent();
        event.setExamId(questionListDTO.getExamId());
        event.setBlankpaperContent(questionListDTO
                        .getQuestionIdList()
                        .stream()
                        .map(String::valueOf)
                        .collect(Collectors.joining(",")));
        blankpaperPublisher.publish(event);
    }
}
