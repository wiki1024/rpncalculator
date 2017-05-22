package com.airwallex.rpncalculator.actions;

import com.airwallex.rpncalculator.ActionRecord;
import com.airwallex.rpncalculator.Stack;

import java.math.BigDecimal;

/**
 * Created by wikic on 5/23/2017.
 */
public class BinaryActionRecord implements ActionRecord {

    public BinaryActionRecord(BigDecimal op1, BigDecimal op2) {
        this.op1 = op1;
        this.op2 = op2;
    }

    public BigDecimal getOp1() {
        return op1;
    }

    public BigDecimal getOp2() {
        return op2;
    }

    private BigDecimal op1;
    private BigDecimal op2;

    @Override
    public void undo(Stack stack) {
        assert stack.size() != 0;
        stack.pop();
        stack.push(op1);
        stack.push(op2);
    }

    @Override
    public String toString() {
        return BinaryActionRecord.class.getSimpleName();
    }
}
