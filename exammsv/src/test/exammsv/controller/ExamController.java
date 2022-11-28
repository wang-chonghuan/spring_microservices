package com.wang.exammsv.controller;

import com.wang.exammsv.domain.dto.AnswersDTO;
import com.wang.exammsv.service.ExamService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/exam")
public class ExamController {

    private final ExamService examService;

    @RequestMapping(value="/fetchblankpaper", method= RequestMethod.GET)
    public ResponseEntity<?> fetchBlankpaper(@RequestParam long studentId, @RequestParam long examId) throws Exception {
        return ResponseEntity.ok().body("");
    }

    @PostMapping("/postanswers")
    public ResponseEntity<?> postAnswers(@RequestBody @Valid AnswersDTO dto) {
        examService.saveAnsweredPaper(dto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }


}
