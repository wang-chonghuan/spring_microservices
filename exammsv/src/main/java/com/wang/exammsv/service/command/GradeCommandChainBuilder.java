package com.wang.exammsv.service.command;
import com.wang.exammsv.domain.StudentExamResult;
import com.wang.exammsv.dto.ManuallyGradeDTO;
import com.wang.exammsv.mq.ScorePublisher;
import com.wang.exammsv.domain.QuestionRepository;
import com.wang.exammsv.domain.StudentExamResultRepository;
import com.wang.exammsv.service.decorator.ResultGradeDecorator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class GradeCommandChainBuilder {
    public void executeCommands(long examId) {
        List<StudentExamResult> resultList = resultRepository.findByExamId(examId);
        List<ResultGradeDecorator> resultDecoratorList = new ArrayList<>();
        resultList.forEach(result -> {
            resultDecoratorList.add(new ResultGradeDecorator(result));
        });
        gradeCommandList.forEach(command -> {
            try {
                command.execute(examId, resultDecoratorList);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }
    public GradeCommandChainBuilder autoGradeProcess() {
        gradeCommandList.clear();
        gradeCommandList.addAll(List.of(
                new CheckGradeStateCommand(GradeCommand.GradeState.submitted),
                new AssemblePaperCommand(questionRepository, resultRepository),
                new CalculateScoreCommand(),
                new SubmitCommand(GradeCommand.GradeState.autograded, resultRepository)));
        return this;
    }
    public GradeCommandChainBuilder bonusAndBroadcastProcess(String expression) {
        gradeCommandList.clear();
        gradeCommandList.addAll(List.of(
                new CheckGradeStateCommand(GradeCommand.GradeState.fullygraded),
                new AddBonusCommand(expression),
                new BroadcastScoreCommand(scorePublisher),
                new SubmitCommand(GradeCommand.GradeState.broadcasted, resultRepository)));
        return this;
    }

    public GradeCommandChainBuilder manuallyGradeProcess(ManuallyGradeDTO manuallyGradeDTO) {
        gradeCommandList.clear();
        gradeCommandList.addAll(List.of(
                new CheckGradeStateCommand(GradeCommand.GradeState.autograded),
                new ManuallyGradeCommand(manuallyGradeDTO),
                new SubmitCommand(GradeCommand.GradeState.fullygraded, resultRepository)));
        return this;
    }

    private final List<GradeCommand> gradeCommandList = new ArrayList<>();
    @Autowired
    private StudentExamResultRepository resultRepository;
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private ScorePublisher scorePublisher;
}
