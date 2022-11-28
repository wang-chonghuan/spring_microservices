package com.wang.studentmsrv.controller;

import com.wang.studentmsrv.domain.dto.AnswersDTO;
import com.wang.studentmsrv.service.ExamService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
    void postanswers(@RequestBody @Valid AnswersDTO dto) {
        examService.saveAnsweredPaper(dto);
    }


}
