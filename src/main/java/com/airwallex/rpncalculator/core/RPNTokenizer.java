package com.airwallex.rpncalculator.core;

import com.airwallex.rpncalculator.RPNException;
import com.airwallex.rpncalculator.StringUtil;
import com.airwallex.rpncalculator.Token;
import com.airwallex.rpncalculator.Tokenizer;

import java.math.BigDecimal;

/**
 * Created by wikic on 5/23/2017.
 */
public class RPNTokenizer implements Tokenizer {
    private OperatorStore operatorStore;

    public RPNTokenizer() {
        this(new RPNOperatorStore());
    }

    public RPNTokenizer(OperatorStore opStore){
        operatorStore=opStore;
    }

    @Override
    public Token getToken(String key) throws RPNException {
        if(StringUtil.isNullOrEmpty(key)) return EmptyToken.Instance;
        Token temp=operatorStore.getOperator(key);
        if(temp!=null) return temp;
        BigDecimal number=getBigDecimal(key);
        return new OperandToken(number);
    }

    private BigDecimal getBigDecimal(String token) throws RPNException {
        BigDecimal result = null;
        try {
            result = new BigDecimal(token);
        } catch (NumberFormatException nfEx) {
            throw new RPNException(String.format("Invald input %s ", token));
        }
        return result;
    }
}
