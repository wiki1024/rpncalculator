package com.airwallex.rpncalculator.core;

import com.airwallex.rpncalculator.BigDecimalFormatter;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Created by wikic on 5/23/2017.
 */
public class RPNFormatter implements BigDecimalFormatter {
    private int scale;

    public RPNFormatter(){
        this(10);
    }

    public RPNFormatter(int scale) {
        this.scale = scale;
    }

    @Override
    public String format(BigDecimal b) {
        return b.setScale(scale, RoundingMode.HALF_UP).stripTrailingZeros().toPlainString();
    }
}
