package com.wang.studentmsrv.controller;

import com.wang.studentmsrv.domain.dto.AnswersDTO;
import com.wang.studentmsrv.service.TakeExamService;
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
@RequestMapping("/takeexam")
public class TakeExamController {
    private final TakeExamService takeExamService;

    @PostMapping("/answer")
    void postAnsweredPaper(@RequestBody @Valid AnswersDTO dto) {
        takeExamService.saveAnsweredPaper(dto);
    }
}