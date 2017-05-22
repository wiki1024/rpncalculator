package com.airwallex.rpncalculator.core;

import com.airwallex.rpncalculator.ActionRecord;
import com.airwallex.rpncalculator.BigDecimalFormatter;
import com.airwallex.rpncalculator.Stack;

import java.math.BigDecimal;
import java.util.Deque;
import java.util.LinkedList;
import java.util.stream.Collectors;

/**
 * Created by wikic on 5/23/2017.
 */
public class RPNStack implements Stack {

    private Deque<BigDecimal> internalStack;
    private Deque<ActionRecord> history;
    private BigDecimalFormatter formatter;

    public RPNStack(BigDecimalFormatter formatter) {
        this.formatter=formatter;
        internalStack = new LinkedList<>();
        history = new LinkedList<>();
    }

    @Override
    public int size() {
        return internalStack.size();
    }

    @Override
    public BigDecimal pop() {
        return internalStack.removeLast();
    }

    @Override
    public void push(BigDecimal item) {
        internalStack.addLast(item);
    }

    @Override
    public void undo() {
        if (history.size() > 0) {
            ActionRecord action=history.pop();
            action.undo(this);
        }
    }

    @Override
    public void clear() {
        internalStack.clear();
        history.clear();
    }

    @Override
    public void record(ActionRecord record) {
        history.push(record);
    }

    @Override
    public String printStack() {
        return internalStack.stream()
                .map(b -> formatter.format(b))
                .collect(Collectors.joining(" ", "Stack: ", ""));
    }

    @Override
    public String printHistory() {
        return history.stream()
                .map(a -> a.toString())
                .collect(Collectors.joining(", ", "Histroy: ", ""));
    }

    @Override
    public BigDecimal[] getOperandStackArray() {
        return internalStack.toArray(new BigDecimal[internalStack.size()]); //bigDecimal is immutable, shallow copy is fine here.
    }

    @Override
    public ActionRecord[] getOperationHistoryArray() {
        return history.toArray(new ActionRecord[history.size()]);
    }

}
