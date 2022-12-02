package com.wang.exammsv.command;

import com.wang.exammsv.dto.ManuallyGradeDTO;
import com.wang.exammsv.dto.AssembledAnswerResultDecorator;

import java.util.List;

public class ManuallyGradeCommand  implements GradeCommand {

    private final ManuallyGradeDTO manuallyGradeDTO;

    /**
     * todo update the manually set scores to the results
     * @param examId
     * @param resultDecoratorList
     * @throws Exception
     */
    @Override
    public void execute(long examId, List<AssembledAnswerResultDecorator> resultDecoratorList) throws Exception {
        if(manuallyGradeDTO.getManullyGradeList().isEmpty()) {
            return;
        }
    }

    public ManuallyGradeCommand(ManuallyGradeDTO manuallyGradeDTO) {
        this.manuallyGradeDTO = manuallyGradeDTO;
    }
}
