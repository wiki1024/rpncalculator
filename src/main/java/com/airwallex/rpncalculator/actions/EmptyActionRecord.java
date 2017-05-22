package com.airwallex.rpncalculator.actions;

import com.airwallex.rpncalculator.ActionRecord;
import com.airwallex.rpncalculator.Stack;

/**
 * Mainly means an *un-undoable" action, for example we can't undo a clear, or we can't undo a undo
 * Undo undo simply means two undo actions
 */
public class EmptyActionRecord implements ActionRecord {

    public static final EmptyActionRecord Instance  = new EmptyActionRecord();

    @Override
    public void undo(Stack stack) {
        //do nothing
    }
}
