package com.airwallex.rpncalculator.core;

import com.airwallex.rpncalculator.Token;
import com.airwallex.rpncalculator.operators.AddOperator;
import com.airwallex.rpncalculator.operators.OperatorToken;
import org.junit.Test;

import java.math.BigDecimal;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.mockito.Mockito.*;

import static org.junit.Assert.*;

/**
 * Created by wikic on 5/23/2017.
 */
public class RPNTokenizerTest {
    @Test
    public void getOperatorToken() throws Exception {
        OperatorStore mockStore= mock(OperatorStore.class);
        when(mockStore.getOperator("+")).thenReturn(new AddOperator());
        RPNTokenizer tokenizer=new RPNTokenizer(mockStore);
        Token token=tokenizer.getToken("+");
        assertThat(token, instanceOf(AddOperator.class));
    }
    @Test
    public void getNumberToken() throws Exception {
        OperatorStore mockStore= mock(OperatorStore.class);
        when(mockStore.getOperator("12345.5678912345")).thenReturn(null);
        RPNTokenizer tokenizer=new RPNTokenizer(mockStore);
        Token token=tokenizer.getToken("12345.5678912345");
        assertThat(token, instanceOf(OperandToken.class));
        OperandToken num=(OperandToken)token;
        assertThat(num.getVal(), equalTo(new BigDecimal("12345.5678912345")));
    }

    @Test
    public void getEmptyToken() throws Exception {
        OperatorStore mockStore= mock(OperatorStore.class);
        when(mockStore.getOperator("")).thenReturn(null);
        RPNTokenizer tokenizer=new RPNTokenizer(mockStore);
        Token token=tokenizer.getToken("");
        assertThat(token, instanceOf(EmptyToken.class));
    }

}