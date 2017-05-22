package com.airwallex.rpncalculator.operators;

import com.airwallex.rpncalculator.RPNException;
import com.airwallex.rpncalculator.Stack;
import org.junit.Test;
import org.mockito.Mockito;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by wikic on 5/23/2017.
 */
public class BinaryOperatorTest {
    @Test
    public void failInsufficientParameters() {
        BinaryOperator binaryOperator = mock(BinaryOperator.class, Mockito.CALLS_REAL_METHODS);
        Stack stack = mock(Stack.class);
        when(binaryOperator.getOperatorSymbol()).thenReturn("+");
        when(stack.size()).thenReturn(1);
        try {
            binaryOperator.Execute(stack, 8);
            fail("expected insufficient parameters.");
        } catch (RPNException rpnEx) {
            assertThat(rpnEx.getMessage(), equalTo("Operator + (position: 8): insufficient parameters."));
        }

    }
}