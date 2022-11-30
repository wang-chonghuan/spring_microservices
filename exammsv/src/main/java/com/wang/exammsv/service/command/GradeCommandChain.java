package com.wang.exammsv.service.command;

import com.wang.exammsv.domain.StudentExamResult;
import com.wang.exammsv.mq.ScorePublisher;
import com.wang.exammsv.mq.event.Score;
import com.wang.exammsv.repository.QuestionRepository;
import com.wang.exammsv.repository.StudentExamResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Service
public class GradeCommandChain {
    private List<GradeCommand> gradeCommandList = new ArrayList<>();

    @Autowired
    private StudentExamResultRepository resultRepository;
    @Autowired
    private ScorePublisher scorePublisher;

    public GradeCommandChain totallyAutoGradeProcess(String expression) {
        gradeCommandList.clear();
        gradeCommandList.addAll(List.of(
                new CheckGradeStateCommand(GradeCommand.GradeState.submitted),
                new AssemblePaperCommand(),
                new CalculateScoreCommand(),
                new AddBonusCommand(expression),
                new BroadcastScoreCommand(scorePublisher),
                new UpdateGradeStateCommand(GradeCommand.GradeState.broadcasted)));
        return this;
    }

    public GradeCommandChain rollbackProcess() {
        gradeCommandList.clear();
        gradeCommandList.addAll(List.of(
                new UpdateGradeStateCommand(GradeCommand.GradeState.submitted)));
        return this;
    }

    public GradeCommandChain assemblePaperProcess() {
        gradeCommandList.clear();
        gradeCommandList.addAll(List.of(
                new CheckGradeStateCommand(GradeCommand.GradeState.submitted),
                new AssemblePaperCommand(),
                new CalculateScoreCommand(),
                new UpdateGradeStateCommand(GradeCommand.GradeState.assembled)));
        return this;
    }

    public GradeCommandChain partiallyGradeProcess() {
        gradeCommandList.clear();
        gradeCommandList.addAll(List.of(
                new CheckGradeStateCommand(GradeCommand.GradeState.assembled),
                new CalculateScoreCommand(),
                new UpdateGradeStateCommand(GradeCommand.GradeState.partialscored)));
        return this;
    }

    public GradeCommandChain manuallyGradeProcess() {
        gradeCommandList.clear();
        gradeCommandList.addAll(List.of(
                new CheckGradeStateCommand(GradeCommand.GradeState.partialscored),
                new UpdateGradeStateCommand(GradeCommand.GradeState.partialscored)));
        return this;
    }

    public GradeCommandChain fullyGradeProcess() {
        gradeCommandList.clear();
        gradeCommandList.addAll(List.of(
                new CheckGradeStateCommand(GradeCommand.GradeState.assembled),
                new CalculateScoreCommand(),
                new UpdateGradeStateCommand(GradeCommand.GradeState.fullyscored)));
        return this;
    }

    public GradeCommandChain addBonusProcess(String expression) {
        gradeCommandList.clear();
        gradeCommandList.addAll(List.of(
                new CheckGradeStateCommand(GradeCommand.GradeState.fullyscored),
                new AddBonusCommand(expression),
                new UpdateGradeStateCommand(GradeCommand.GradeState.fullyscored)));
        return this;
    }

    public GradeCommandChain broadcastScoreProcess() {
        gradeCommandList.clear();
        gradeCommandList.addAll(List.of(
                new CheckGradeStateCommand(GradeCommand.GradeState.fullyscored),
                new BroadcastScoreCommand(scorePublisher)));
        return this;
    }

    public GradeCommandChain executeCommands(long examId) {
        List<StudentExamResult> resultList = resultRepository.findByExamId(examId);
        gradeCommandList.forEach(command -> {
            try {
                command.execute(resultList);
            } catch (BreakChainException e) {
                throw new RuntimeException(e);
            }
        });
        return this;
    }

}
