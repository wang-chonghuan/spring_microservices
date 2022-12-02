package com.wang.exammsv.service;

import com.wang.exammsv.dto.BroadcastDTO;
import com.wang.exammsv.dto.ManuallyGradeDTO;
import com.wang.exammsv.service.command.GradeCommandChainBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class GradeService {
    @Autowired
    private GradeCommandChainBuilder gradeCommandChainBuilder;

    public void broadcastScoreWithBonus(BroadcastDTO dto) {
        gradeCommandChainBuilder.bonusAndBroadcastProcess(dto.getExpression()).executeCommands(dto.getExamId());
    }

    public void autoGrade(long examId) {
        gradeCommandChainBuilder.autoGradeProcess().executeCommands(examId);
    }

    public void manuallyGrade(ManuallyGradeDTO dto) {
        gradeCommandChainBuilder.manuallyGradeProcess(dto).executeCommands(dto.getExamId());
    }
}
