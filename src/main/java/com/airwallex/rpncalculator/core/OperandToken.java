package com.airwallex.rpncalculator.core;

import com.airwallex.rpncalculator.Action;
import com.airwallex.rpncalculator.RPNException;
import com.airwallex.rpncalculator.Stack;
import com.airwallex.rpncalculator.Token;
import com.airwallex.rpncalculator.actions.PushAction;

import java.math.BigDecimal;

/**
 * Created by wikic on 5/23/2017.
 */
public class OperandToken implements Token {

    private BigDecimal val;

    public OperandToken(BigDecimal val) {
        this.val = val;
    }

    @Override
    public Action Execute(Stack stack, int pos) throws RPNException {
        stack.push(val);
        return new PushAction();
    }
}
