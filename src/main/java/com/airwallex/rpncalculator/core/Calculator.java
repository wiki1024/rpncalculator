package com.airwallex.rpncalculator.core;

import com.airwallex.rpncalculator.*;
import com.airwallex.rpncalculator.actions.EmptyActionRecord;

/**
 * Created by wikic on 5/23/2017.
 */
public class Calculator {
    private Stack stack;
    private Parser parser;
    private Tokenizer tokenizer;

    public Calculator(Stack stack, Parser parser, Tokenizer tokenizer) {
        this.stack = stack;
        this.parser = parser;
        this.tokenizer = tokenizer;
    }

    public void process(String input) throws RPNException {
        String[] keys = parser.parse(input);
        int pos = 1;
        for (String key : keys) {
            Token token = tokenizer.getToken(key);
            ActionRecord action = token.Execute(stack, pos++);
            if (action instanceof EmptyActionRecord) continue;
            stack.record(action);
        }
    }

    public String print() {
        return stack.printStack();
    }
}
