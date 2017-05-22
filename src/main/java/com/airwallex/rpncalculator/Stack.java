package com.airwallex.rpncalculator;

import java.math.BigDecimal;

/**
 * Created by wikic on 5/23/2017.
 */
public interface Stack {
    int size();
    BigDecimal pop();
    void push(BigDecimal item);
    void undo();
    void clear();
    void record(ActionRecord record);
    String printStack();
    String printHistory();
    BigDecimal[] getOperandStackArray();
    ActionRecord[] getOperationHistoryArray();
}
