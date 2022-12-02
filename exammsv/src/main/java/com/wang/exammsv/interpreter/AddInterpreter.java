package com.wang.exammsv.interpreter;

public class AddInterpreter implements Interpreter {

    private final Interpreter firstInterpreter;
    private final Interpreter secondInterpreter;

    public AddInterpreter(Interpreter firstInterpreter, Interpreter secondInterpreter) {
        this.firstInterpreter = firstInterpreter;
        this.secondInterpreter = secondInterpreter;
    }

    @Override
    public double interpret() {
        return firstInterpreter.interpret() + secondInterpreter.interpret();
    }
}
