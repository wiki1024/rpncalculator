package com.airwallex.rpncalculator.operators;

import com.airwallex.rpncalculator.ActionRecord;
import com.airwallex.rpncalculator.RPNException;
import com.airwallex.rpncalculator.Stack;
import com.airwallex.rpncalculator.actions.EmptyActionRecord;

/**
 * Created by wikic on 5/23/2017.
 */
public class ClearOperator implements OperatorToken {
    @Override
    public ActionRecord Execute(Stack stack, int pos) throws RPNException {
        stack.clear();
        return EmptyActionRecord.Instance;
    }

    @Override
    public String getOperatorSymbol() {
        return "clear";
    }
}
