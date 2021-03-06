package com.airwallex.rpncalculator.operators;

import com.airwallex.rpncalculator.RPNException;

import java.math.BigDecimal;

/**
 * Created by wikic on 5/23/2017.
 */
public class DivideOperator extends BinaryOperator {

    private int scale;

    public DivideOperator(){
        this(15);
    }

    public DivideOperator(int scale) {
        this.scale = scale;
    }

    @Override
    protected BigDecimal compute(BigDecimal op1, BigDecimal op2) throws RPNException {
        if (op2.compareTo(BigDecimal.ZERO) == 0) throw new RPNException("Divisor cannot be zero.");
        return op1.divide(op2, scale, BigDecimal.ROUND_HALF_UP);
    }

    @Override
    public String getOperatorSymbol() {
        return "/";
    }
}
