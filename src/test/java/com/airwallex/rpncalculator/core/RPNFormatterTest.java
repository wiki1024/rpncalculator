package com.airwallex.rpncalculator.core;

import org.junit.Test;

import java.math.BigDecimal;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.*;

/**
 * Created by wikic on 5/23/2017.
 */
public class RPNFormatterTest {
    @Test
    public void format() throws Exception {
        RPNFormatter formatter = new RPNFormatter();
        String result = formatter.format(new BigDecimal("471404.5207907959206735006666362"));
        assertThat(result, equalTo("471404.5207907959"));
    }

}