package com.airwallex.rpncalculator;

/**
 * Created by wikic on 5/23/2017.
 */
public interface Tokenizer {
    Token getToken(String key) throws RPNException;
}
