package com.airwallex.rpncalculator.core;

import com.airwallex.rpncalculator.operators.*;

import java.util.HashMap;

/**
 * Created by wikic on 5/23/2017.
 */
public interface OperatorStore {
    OperatorToken getOperator(String key);
}
