package com.airwallex.rpncalculator.operators;

import com.airwallex.rpncalculator.Action;
import com.airwallex.rpncalculator.RPNException;
import com.airwallex.rpncalculator.Stack;
import com.airwallex.rpncalculator.Token;
import com.airwallex.rpncalculator.actions.BinaryAction;

import java.math.BigDecimal;

/**
 * Created by wikic on 5/23/2017.
 */
public abstract class BinaryOperator implements OperatorToken {

    protected abstract BigDecimal compute(BigDecimal op1, BigDecimal op2) throws RPNException;

    @Override
    public Action Execute(Stack stack, int pos) throws RPNException {
        if (stack.size() < 2)
            throw new RPNException(String.format("Operator %s (position: %d): insufficient parameters.", getOperatorSymbol(), pos));
        BigDecimal op2 = stack.pop();
        BigDecimal op1 = stack.pop();
        try {
            BigDecimal result = compute(op1, op2);
            stack.push(result);
            return new BinaryAction(op1, op2);
        } catch (RPNException rpnEx) {
            stack.push(op1);
            stack.push(op2);
            throw rpnEx;
        }
    }
}
