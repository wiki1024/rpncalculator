package com.airwallex.rpncalculator.core;

import org.junit.Test;

import java.math.BigDecimal;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.*;

/**
 * Created by wikic on 5/23/2017.
 */
public class RPNStackTest {
    @Test
    public void printStack() throws Exception {
        RPNStack stack=new RPNStack(new RPNFormatter());
        stack.push(new BigDecimal("5.58209749445923078164062862"));
        stack.push(new BigDecimal("4.482337867831652712019091"));
        stack.push(new BigDecimal("3.14159265358979323846264338"));
        stack.push(new BigDecimal("2.327950288419716939937510"));
        stack.push(new BigDecimal("1.000"));
        stack.push(new BigDecimal("2.1000"));
        stack.push(new BigDecimal("3.20000000"));
        stack.push(new BigDecimal("0.0000"));
        assertThat(stack.printStack(),equalTo("Stack: 5.5820974945 4.4823378678 3.1415926536 2.3279502884 1 2.1 3.2 0"));
    }

}