package com.wang.TeacherMicroservice.controller;

import com.wang.TeacherMicroservice.dto.QuestionListDTO;
import com.wang.TeacherMicroservice.service.BlankpaperService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/blankpaper")
public class BlankpaperController {
    private final BlankpaperService blankpaperService;

    @PostMapping
    void createBlankpaper(@RequestBody @Valid QuestionListDTO questionListDTO) {
        blankpaperService.createBlankpaper(questionListDTO);
    }
}
