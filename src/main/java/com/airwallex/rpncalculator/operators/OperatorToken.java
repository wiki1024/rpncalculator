package com.airwallex.rpncalculator.operators;

import com.airwallex.rpncalculator.Token;

/**
 * Created by wikic on 5/23/2017.
 */
public interface OperatorToken extends Token {
    String getOperatorSymbol();
}
