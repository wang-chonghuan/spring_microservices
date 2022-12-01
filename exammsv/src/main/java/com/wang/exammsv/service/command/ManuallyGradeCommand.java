package com.wang.exammsv.service.command;

import com.wang.exammsv.dto.ManuallyGradeDTO;
import com.wang.exammsv.service.decorator.ResultGradeDecorator;

import java.util.List;

public class ManuallyGradeCommand  implements GradeCommand {

    private final ManuallyGradeDTO manuallyGradeDTO;

    @Override
    public void execute(long examId, List<ResultGradeDecorator> resultDecoratorList) throws Exception {
        if(manuallyGradeDTO.getManullyGradeList().isEmpty()) {
            return;
        } else {
            // todo update the manually set scores to the results
        }
    }

    public ManuallyGradeCommand(ManuallyGradeDTO manuallyGradeDTO) {
        this.manuallyGradeDTO = manuallyGradeDTO;
    }
}
