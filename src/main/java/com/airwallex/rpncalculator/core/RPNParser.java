package com.airwallex.rpncalculator.core;

import com.airwallex.rpncalculator.Parser;

/**
 * Created by wikic on 5/23/2017.
 */
public class RPNParser implements Parser {
    private String seprator;

    public RPNParser(){
        this("\\s+");
    }

    public RPNParser(String seprator) {
        this.seprator = seprator;
    }

    @Override
    public String[] parse(String input) {
        return input.split(seprator);
    }
}
