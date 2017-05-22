package com.airwallex.rpncalculator;

import java.math.BigDecimal;

/**
 * Created by wikic on 5/23/2017.
 */
public interface SqrtAlgo {
    BigDecimal doSqrt(BigDecimal operand, BigDecimal precison);
}
