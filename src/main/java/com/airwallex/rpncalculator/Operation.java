package com.airwallex.rpncalculator;

import java.math.BigDecimal;

/**
 * Created by wikic on 5/19/2017.
 */
@FunctionalInterface
public interface Operation {
    BigDecimal compute(BigDecimal op1, BigDecimal op2) throws RPNException;
}
