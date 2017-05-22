package com.airwallex.rpncalculator;

/**
 * Created by wikic on 5/23/2017.
 */
public interface Token {
    Action Execute (Stack stack, int pos) throws RPNException;
}
