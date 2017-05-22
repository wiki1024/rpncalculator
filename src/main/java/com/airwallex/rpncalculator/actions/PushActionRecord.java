package com.airwallex.rpncalculator.actions;

import com.airwallex.rpncalculator.ActionRecord;
import com.airwallex.rpncalculator.Stack;

/**
 * Created by wikic on 5/23/2017.
 */
public class PushActionRecord implements ActionRecord {
    @Override
    public void undo(Stack stack) {
        assert stack.size() != 0;
        stack.pop();
    }

    @Override
    public String toString() {
        return PushActionRecord.class.getSimpleName();
    }
}
