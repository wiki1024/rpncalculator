package com.airwallex.rpncalculator.operators;

import com.airwallex.rpncalculator.RPNException;
import com.airwallex.rpncalculator.core.RPNTokenizer;
import org.junit.Test;

import java.math.BigDecimal;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.*;

/**
 * Created by wikic on 5/23/2017.
 */
public class DivideOperatorTest {
    @Test
    public void compute() throws Exception {
        DivideOperator operator = new DivideOperator(15);
        BigDecimal result = operator.compute(new BigDecimal(10), new BigDecimal(3));
        assertThat(result, equalTo(new BigDecimal("3.333333333333333")));
    }

    @Test
    public void failForZero() {
        try{
            DivideOperator operator = new DivideOperator(15);
            BigDecimal result = operator.compute(new BigDecimal(10), new BigDecimal(0));
            fail("Expected");
        }
        catch (RPNException ex){
            assertThat(ex.getMessage(),equalTo("Divisor cannot be zero."));
        }
    }

}