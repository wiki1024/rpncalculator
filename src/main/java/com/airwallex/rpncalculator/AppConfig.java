package com.airwallex.rpncalculator;

import com.airwallex.rpncalculator.core.*;
import org.springframework.context.annotation.*;
/**
 * Created by wikic on 5/23/2017.
 */
@Configuration
public class AppConfig {

    @Bean
    public Parser parser(){
        return new RPNParser();
    }

    @Bean
    public OperatorStore operatorStore(){
        return new RPNOperatorStore();
    }

    @Bean
    public Tokenizer tokenizer(OperatorStore operatorStore){
        return new RPNTokenizer(operatorStore);
    }

    @Bean
    public BigDecimalFormatter bigDecimalFormatter(){
        return new RPNFormatter();
    }

    @Bean
    public Stack stack(BigDecimalFormatter bigDecimalFormatter){
        return new RPNStack(bigDecimalFormatter);
    }

    @Bean
    public Calculator calculator(Stack stack, Parser parser, Tokenizer tokenizer){
        return new Calculator(stack,parser,tokenizer);
    }
}
