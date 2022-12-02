package com.wang.exammsv.interpreter;

public class OperatorUtil {
    public static boolean isSymbol(String symbol) {
        return symbol.equals("+") || symbol.equals("*");
    }

    public static Interpreter getExpressionObject(
            Interpreter firstInterpreter, Interpreter secondInterpreter, String symbol) {
        if(symbol.equals("*")) {
            return new MultiplyInterpreter(firstInterpreter, secondInterpreter);
        } else if(symbol.equals("+")) {
            return new AddInterpreter(firstInterpreter, secondInterpreter);
        }
        return null;
    }
}
