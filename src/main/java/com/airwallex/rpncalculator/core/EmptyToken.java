package com.airwallex.rpncalculator.core;

import com.airwallex.rpncalculator.ActionRecord;
import com.airwallex.rpncalculator.RPNException;
import com.airwallex.rpncalculator.Stack;
import com.airwallex.rpncalculator.Token;
import com.airwallex.rpncalculator.actions.EmptyActionRecord;

/**
 * Created by wikic on 5/23/2017.
 */
public class EmptyToken implements Token {
    public static final EmptyToken Instance  = new EmptyToken();
    @Override
    public ActionRecord Execute(Stack stack, int pos) throws RPNException {
        return EmptyActionRecord.Instance;
    }
}
