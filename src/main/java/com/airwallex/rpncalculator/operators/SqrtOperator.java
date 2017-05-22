package com.airwallex.rpncalculator.operators;

import com.airwallex.rpncalculator.BigDecimalFormatter;
import com.airwallex.rpncalculator.RPNException;
import com.airwallex.rpncalculator.SqrtAlgo;
import com.airwallex.rpncalculator.core.NewtonSqrt2;
import com.airwallex.rpncalculator.core.RPNFormatter;

import java.math.BigDecimal;

/**
 * Created by wikic on 5/23/2017.
 */
public class SqrtOperator extends UnaryOperator {

    private BigDecimal precE;

    private SqrtAlgo sqrtAlgo;


    public SqrtOperator() {
        this(20, new NewtonSqrt2());
    }

    public SqrtOperator(int precision, SqrtAlgo sqrtImpl) {
        String e = "-0.";
        for (int i = 0; i < precision; i++) {
            e += "0";
        }
        e += "1";
        precE = new BigDecimal(e);
        sqrtAlgo = sqrtImpl;
    }


    @Override
    protected BigDecimal compute(BigDecimal op1) throws RPNException {
        if (op1.compareTo(BigDecimal.ZERO) < 0) {
            throw new RPNException(String.format("A negative value %s cannot perform a square root.", op1));
        }
        return sqrtAlgo.doSqrt(op1, precE);
    }

    @Override
    public String getOperatorSymbol() {
        return "sqrt";
    }
}
