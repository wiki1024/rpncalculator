package com.airwallex.rpncalculator.core;

import com.airwallex.rpncalculator.SqrtAlgo;

import java.math.BigDecimal;

/**
 * Created by wikic on 5/23/2017.
 */
public class NewtonSqrt2 implements SqrtAlgo{
    private static final BigDecimal TWO = new BigDecimal(2);
    @Override
    public BigDecimal doSqrt(BigDecimal operand, BigDecimal precison) {
        if (operand.compareTo(BigDecimal.ZERO) == 0) return BigDecimal.ZERO;
        BigDecimal result = operand;
        while (true) {
            BigDecimal last = result;
            result = result.add(operand.divide(result, 20, BigDecimal.ROUND_HALF_UP)).divide(TWO, 20, BigDecimal.ROUND_HALF_UP);
            if (result.add(last.negate()).abs().add(precison).compareTo(BigDecimal.ZERO) < 0)
                break;
        }
        return result;
    }
}
