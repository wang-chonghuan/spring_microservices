package com.wang.exammsv.interpreter;

import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

@Slf4j
public class BonusCalculator {
    /**
     *
     * @param bonusExpression  "10 + 1.2 * 1 +"
     * @param oriScore 50
     * @return 73
     */
    public static double interpret(String bonusExpression, double oriScore) {
        if(Objects.equals(bonusExpression, "")) {
            return oriScore;
        }
        var expression = String.valueOf(oriScore) + " " + bonusExpression;
        return ExpressionParser.parse(expression);
    }
}
