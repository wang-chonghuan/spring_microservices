package com.wang.exammsv.service.interpreter;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BonusCalculator {
    /**
     *
     * @param bonusExpression 10 + 1.2 * 1 +
     * @param oriScore 50
     * @return 73
     */
    public static double interpret(String bonusExpression, double oriScore) {
        var expression = String.valueOf(oriScore) + " " + bonusExpression;
        var score = ExpressionParser.parse(expression);
        return score;
    }
}
