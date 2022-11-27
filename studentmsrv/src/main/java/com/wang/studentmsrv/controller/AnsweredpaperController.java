package com.wang.studentmsrv.controller;

import com.wang.studentmsrv.domain.dto.QuestionWithAnswerListDTO;
import com.wang.studentmsrv.service.AnsweredpaperService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/answeredpaper")
public class AnsweredpaperController {
    private final AnsweredpaperService answeredpaperService;

    @PostMapping
    void postAnsweredpaper(@RequestBody @Valid QuestionWithAnswerListDTO aqListDTO) {
        answeredpaperService.postAnsweredpaper(aqListDTO);
    }
}