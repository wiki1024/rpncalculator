package com.airwallex.rpncalculator.actions;

import com.airwallex.rpncalculator.Action;
import com.airwallex.rpncalculator.Stack;

import java.math.BigDecimal;

/**
 * Created by wikic on 5/23/2017.
 */
public class UnaryAction implements Action {

    private BigDecimal op;

    public BigDecimal getOp() {
        return op;
    }

    public UnaryAction(BigDecimal op) {
        this.op = op;
    }

    @Override
    public void undo(Stack stack) {
        assert stack.size() != 0;
        stack.pop();
        stack.push(op);
    }

    @Override
    public String toString() {
        return UnaryAction.class.getSimpleName();
    }
}
