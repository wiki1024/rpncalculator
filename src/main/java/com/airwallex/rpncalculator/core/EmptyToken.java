package com.airwallex.rpncalculator.core;

import com.airwallex.rpncalculator.Action;
import com.airwallex.rpncalculator.RPNException;
import com.airwallex.rpncalculator.Stack;
import com.airwallex.rpncalculator.Token;
import com.airwallex.rpncalculator.actions.EmptyAction;

/**
 * Created by wikic on 5/23/2017.
 */
public class EmptyToken implements Token {
    public static final EmptyToken Instance  = new EmptyToken();
    @Override
    public Action Execute(Stack stack, int pos) throws RPNException {
        return EmptyAction.Instance;
    }
}
