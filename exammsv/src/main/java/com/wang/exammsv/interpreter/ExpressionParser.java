package com.wang.exammsv.interpreter;

import java.util.LinkedList;

public class ExpressionParser {
    private static final LinkedList<Interpreter> queue = new LinkedList<Interpreter>();

    public static double parse(String oriStr) {
        String[] splitStrList = oriStr.split(" ");
        for(String curStr: splitStrList) {
            if(OperatorUtil.isSymbol(curStr)) {
                Interpreter firstInterpreter = queue.pop();
                Interpreter secondInterpreter = queue.pop();
                Interpreter expressionObject = OperatorUtil.getExpressionObject(
                        firstInterpreter, secondInterpreter, curStr);
                assert expressionObject != null;
                queue.push(new NumberInterpreter(expressionObject.interpret()));
            } else {
                queue.push(new NumberInterpreter(curStr));
            }
        }
        return queue.pop().interpret();
    }
}
