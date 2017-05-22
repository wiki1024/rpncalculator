package com.airwallex.rpncalculator.operators;

import com.airwallex.rpncalculator.Action;
import com.airwallex.rpncalculator.RPNException;
import com.airwallex.rpncalculator.Stack;
import com.airwallex.rpncalculator.Token;
import com.airwallex.rpncalculator.actions.UnaryAction;

import java.math.BigDecimal;

/**
 * Created by wikic on 5/23/2017.
 */
public abstract class UnaryOperator implements OperatorToken {

    protected abstract BigDecimal compute(BigDecimal op1) throws RPNException;
    @Override
    public Action Execute(Stack stack, int pos) throws RPNException {
        if (stack.size() < 1)
            throw new RPNException(String.format("Operator %s (position: %d): insufficient parameters.", getOperatorSymbol(), pos));
        BigDecimal op1 = stack.pop();
        try {
            BigDecimal result = compute(op1);
            stack.push(result);
            return new UnaryAction(op1);
        }
        catch (RPNException rpnEx){
            stack.push(op1);
            throw rpnEx;
        }
    }
}
