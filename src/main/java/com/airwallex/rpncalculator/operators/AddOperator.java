package com.airwallex.rpncalculator.operators;


import java.math.BigDecimal;

/**
 * Created by wikic on 5/23/2017.
 */
public class AddOperator extends BinaryOperator{
    @Override
    protected BigDecimal compute(BigDecimal op1, BigDecimal op2) {
        return op1.add(op2);
    }

    @Override
    public String getOperatorSymbol() {
        return "+";
    }
}
