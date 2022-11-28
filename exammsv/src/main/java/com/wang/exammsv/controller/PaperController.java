package com.wang.exammsv.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.wang.exammsv.domain.dto.QuestionSettingsDTO;
import com.wang.exammsv.service.PaperService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/paper")
public class PaperController {
    private final PaperService paperService;

    // todo
    @RequestMapping(value="/createquestion", method= RequestMethod.POST)
    public ResponseEntity<?> createQuestion(@RequestBody Object dto) throws Exception {
        return ResponseEntity.ok().body("");
    }

    // todo
    @RequestMapping(value="/viewquestions", method= RequestMethod.GET)
    public ResponseEntity<?> viewQuestions(@RequestParam long studentId, @RequestParam long examId) throws Exception {
        return ResponseEntity.ok().body("");
    }

    @RequestMapping(value="/createpaper", method= RequestMethod.POST)
    public ResponseEntity<?> createPaper(@RequestBody @Valid QuestionSettingsDTO questionSettingsDTO) {
        try {
            paperService.createBlankpaper(questionSettingsDTO);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok().body("");
    }


}
