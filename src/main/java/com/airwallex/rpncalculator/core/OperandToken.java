package com.airwallex.rpncalculator.core;

import com.airwallex.rpncalculator.ActionRecord;
import com.airwallex.rpncalculator.RPNException;
import com.airwallex.rpncalculator.Stack;
import com.airwallex.rpncalculator.Token;
import com.airwallex.rpncalculator.actions.PushActionRecord;

import java.math.BigDecimal;

/**
 * Created by wikic on 5/23/2017.
 */
public class OperandToken implements Token {

    public BigDecimal getVal() {
        return val;
    }

    private BigDecimal val;

    public OperandToken(BigDecimal val) {
        this.val = val;
    }

    @Override
    public ActionRecord Execute(Stack stack, int pos) throws RPNException {
        stack.push(val);
        return new PushActionRecord();
    }
}
