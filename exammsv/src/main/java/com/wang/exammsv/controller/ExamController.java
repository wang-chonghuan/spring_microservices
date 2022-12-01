package com.wang.exammsv.controller;

import com.wang.exammsv.domain.Blankpaper;
import com.wang.exammsv.dto.AnswerDTO;
import com.wang.exammsv.repository.BlankpaperRepository;
import com.wang.exammsv.service.ExamService;
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
@RequestMapping("/exam")
public class ExamController {

    private final ExamService examService;
    private final BlankpaperRepository blankpaperRepository;

    @RequestMapping(value="/fetchblankpaper", method= RequestMethod.GET)
    public ResponseEntity<?> fetchBlankpaper(@RequestParam long paperId) throws Exception {
        Optional<Blankpaper> paper = blankpaperRepository.findById(paperId);
        return ResponseEntity.ok().body(paper.get().getPaperContent());
    }

    @PostMapping("/postanswers")
    public ResponseEntity<?> postAnswers(@RequestBody @Valid AnswerDTO dto) {
        examService.saveAnsweredPaper(dto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
