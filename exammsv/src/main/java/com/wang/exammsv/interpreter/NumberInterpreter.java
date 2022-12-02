package com.wang.exammsv.interpreter;

public class NumberInterpreter implements Interpreter {

    private final double number;
    NumberInterpreter(double number) {
        this.number = number;
    }
    NumberInterpreter(String number) {
        this.number = Double.parseDouble(number);
    }

    @Override
    public double interpret() {
        return this.number;
    }
}
