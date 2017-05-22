package com.airwallex.rpncalculator.core;

import com.airwallex.rpncalculator.operators.*;

import java.util.HashMap;

/**
 * Created by wikic on 5/23/2017.
 */
public class RPNOperatorStore implements OperatorStore {
    private HashMap<String,OperatorToken> operators;

    public RPNOperatorStore(){
        //extension: could use reflection to auto populate the HashMap
        operators =new HashMap<>();
        operators.put("+", new AddOperator());
        operators.put("-", new MinusOperator());
        operators.put("*", new MultiplyOperator());
        operators.put("/", new DivideOperator());
        operators.put("sqrt", new SqrtOperator());
        operators.put("undo", new UndoOperator());
        operators.put("clear", new ClearOperator());
    }

    public OperatorToken getOperator(String key){
        return operators.get(key);
    }
}
