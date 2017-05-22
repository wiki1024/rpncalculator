package com.airwallex.rpncalculator.actions;

import com.airwallex.rpncalculator.Action;
import com.airwallex.rpncalculator.Stack;

/**
 * Mainly means an *un-undoable" action, for example we can't undo a clear, or we can't undo a undo
 * Undo undo simply means two undo actions
 */
public class EmptyAction implements Action {

    public static final EmptyAction Instance  = new EmptyAction();

    @Override
    public void undo(Stack stack) {
        //do nothing
    }
}
