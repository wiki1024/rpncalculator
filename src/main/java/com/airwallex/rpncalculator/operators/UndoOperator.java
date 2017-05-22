package com.airwallex.rpncalculator.operators;

import com.airwallex.rpncalculator.Action;
import com.airwallex.rpncalculator.RPNException;
import com.airwallex.rpncalculator.Stack;
import com.airwallex.rpncalculator.actions.EmptyAction;

/**
 * Created by wikic on 5/23/2017.
 */
public class UndoOperator implements OperatorToken {
    @Override
    public Action Execute(Stack stack, int pos) throws RPNException {
         stack.undo();
         return EmptyAction.Instance;
    }

    @Override
    public String getOperatorSymbol() {
        return "undo";
    }
}
