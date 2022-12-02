package com.wang.exammsv.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.wang.exammsv.domain.Blankpaper;
import com.wang.exammsv.domain.BlankpaperRepository;
import com.wang.exammsv.dto.AnswerDTO;
import com.wang.exammsv.dto.QuestionSettingDTO;
import com.wang.exammsv.service.PaperService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping
public class PaperController {
    private final PaperService paperService;
    private final BlankpaperRepository blankpaperRepository;

    @RequestMapping(value="/paper/createpaper", method= RequestMethod.POST)
    public ResponseEntity<?> createPaper(@RequestBody @Valid QuestionSettingDTO questionSettingDTO) {
        try {
            paperService.createBlankpaper(questionSettingDTO);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok().body("");
    }

    @RequestMapping(value="/exam/fetchblankpaper", method= RequestMethod.GET)
    public ResponseEntity<?> fetchBlankpaper(@RequestParam long examId) throws Exception {
        Optional<Blankpaper> paper = blankpaperRepository.findByExamId(examId).stream().findFirst();
        return ResponseEntity.ok().body(paper.get().getPaperContent());
    }

    @PostMapping("/exam/postanswers")
    public ResponseEntity<?> postAnswers(@RequestBody @Valid AnswerDTO dto) {
        paperService.saveAnsweredPaper(dto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }


}
