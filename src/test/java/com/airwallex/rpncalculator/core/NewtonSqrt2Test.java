package com.airwallex.rpncalculator.core;

import com.sun.org.apache.bcel.internal.generic.NEW;
import org.junit.Test;

import java.math.BigDecimal;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.*;

/**
 * Created by wikic on 5/23/2017.
 */
public class NewtonSqrt2Test {
    @Test
    public void doSqrt() throws Exception {
        NewtonSqrt2 sqrt=new NewtonSqrt2();
        BigDecimal preE= new BigDecimal("-0.000000000000000000001");
        BigDecimal result = sqrt.doSqrt(new BigDecimal("222222222222"),preE);
        assertThat(result,equalTo(new BigDecimal("471404.52079079598067350067")));
    }

}