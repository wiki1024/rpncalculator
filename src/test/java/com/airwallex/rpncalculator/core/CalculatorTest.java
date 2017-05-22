package com.airwallex.rpncalculator.core;

import com.airwallex.rpncalculator.Parser;
import com.airwallex.rpncalculator.RPNException;
import com.airwallex.rpncalculator.Stack;
import com.airwallex.rpncalculator.Tokenizer;
import org.junit.Test;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.*;

/**
 * Created by wikic on 5/23/2017.
 */
public class CalculatorTest {

    @Test
    public void insufficientParameterStack() {
        Stack stack = new RPNStack(new RPNFormatter());
        Parser parser = new RPNParser();
        Tokenizer tokenizer = new RPNTokenizer(new RPNOperatorStore());

        Calculator calc = new Calculator(stack, parser, tokenizer);
        try {
            calc.process("1 2 3 * 5 + * * 6 5");
            fail();
        } catch (RPNException rpnEx) {

        }
        assertThat(calc.print(), equalTo("Stack: 11"));
    }

    @Test
    public void undoAndClear() throws Exception {
        Stack stack = new RPNStack(new RPNFormatter());
        Parser parser = new RPNParser();
        Tokenizer tokenizer = new RPNTokenizer(new RPNOperatorStore());

        Calculator calc = new Calculator(stack, parser, tokenizer);

        calc.process("5 4 3 2");
        calc.process("undo undo *");

        assertThat(calc.print(), equalTo("Stack: 20"));

        calc.process("clear 3 4 -");
        assertThat(calc.print(), equalTo("Stack: -1"));

    }

}