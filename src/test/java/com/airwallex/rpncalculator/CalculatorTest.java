//package com.airwallex.rpncalculator;
//
//import org.junit.Test;
//
//import java.math.BigDecimal;
//
//import static org.hamcrest.Matchers.*;
//import static org.junit.Assert.*;
//
///**
// * use equalTo insteadOf compareEqualTo for BigDecimal, need to match scale
// */
//public class CalculatorTest {
//    @Test
//    public void testSimplePush() throws Exception {
//        Calculator calc = new Calculator(8);
//        calc.processInput("5.58209749445923078164062862 4.482337867831652712019091       3.14159265358979323846264338 2.327950288419716939937510");
//        BigDecimal[] stackArray = calc.getOperandStackArray();
//        assertThat(stackArray[3], equalTo(new BigDecimal("2.327950288419716939937510")));
//        assertThat(stackArray[2], equalTo(new BigDecimal("3.14159265358979323846264338")));
//        assertThat(stackArray[1], equalTo(new BigDecimal("4.482337867831652712019091")));
//        assertThat(stackArray[0], equalTo(new BigDecimal("5.58209749445923078164062862")));
//        Action[] records = calc.getOperationHistoryArray();
//        assertThat(records[0], instanceOf(Calculator.PushRPNActionRecord.class));
//        assertThat(records[1], instanceOf(Calculator.PushRPNActionRecord.class));
//        assertThat(records[2], instanceOf(Calculator.PushRPNActionRecord.class));
//        assertThat(records[3], instanceOf(Calculator.PushRPNActionRecord.class));
//    }
//
//
//    @Test
//    public void testCalculation() throws Exception {
//        Calculator calc = new Calculator();
//        calc.processInput("9 8 7 6 5");
//
//        //test minus
//        calc.processInput("5.58209749445923078164062862 4.482337867831652712019091 -");
//        BigDecimal[] stackArray = calc.getOperandStackArray();
//        assertThat(stackArray[5], equalTo(new BigDecimal("1.09975962662757806962153762")));
//
//        calc.processInput("clear");
//        //test add
//        calc.processInput("5.1428571428571428571428571428571 4.482337867831652712019091 +");
//        stackArray = calc.getOperandStackArray();
//        assertThat(stackArray[0], equalTo(new BigDecimal("9.6251950106887955691619481428571")));
//
//        calc.processInput("clear");
//        //test divide
//        calc.processInput("10 3 /");
//        stackArray = calc.getOperandStackArray();
//        assertThat(stackArray[0], equalTo(new BigDecimal("3.333333333333333")));
//
//        //test multiply
//        calc.processInput("3.33 * ");
//        stackArray = calc.getOperandStackArray();
//        assertThat(stackArray[0], equalTo(new BigDecimal("11.09999999999999889")));
//
//    }
//
//    @Test
//    public void testInsufficientParameters() throws Exception {
//        Calculator calc = new Calculator(8);
//        try {
//            calc.processInput("1 2 3 * 5 + * * 6 5");
//            fail("expected insufficient parameters.");
//        } catch (RPNException rpnEx) {
//            assertThat(rpnEx.getMessage(), equalTo("Operator * (position: 8): insufficient parameters."));
//        }
//        BigDecimal[] stackArray = calc.getOperandStackArray();
//        assertThat(stackArray.length, is(1));
//        assertThat(stackArray[0], equalTo(new BigDecimal("11")));
//
//        calc.processInput("clear");
//        try {
//            calc.processInput("1 2 3 4 5 6 7 clear sqrt");
//            fail("expected sqrt insufficient parameters.");
//        } catch (RPNException rpnEx) {
//            assertThat(rpnEx.getMessage(), equalTo("Operator sqrt (position: 9): insufficient parameters."));
//        }
//    }
//
//    @Test
//    public void testUndo() throws Exception {
//        Calculator calc = new Calculator(8);
//        calc.processInput("5 4 3 2");
//        calc.processInput("undo undo *");
//        BigDecimal[] stackArray = calc.getOperandStackArray();
//        assertThat(stackArray.length, is(1));
//        assertThat(stackArray[0], equalTo(new BigDecimal("20")));
//
//        calc.processInput(" 5 *  ");
//        calc.processInput("undo");
//        stackArray = calc.getOperandStackArray();
//        assertThat(stackArray.length, is(2));
//        assertThat(stackArray[0], equalTo(new BigDecimal("20")));
//        assertThat(stackArray[1], equalTo(new BigDecimal("5")));
//
//    }
//
//    @Test
//    public void testZeroDivisor() {
//        Calculator calc = new Calculator(8);
//        try {
//            calc.processInput("5 4 3 2 6 0 /");
//            fail("expected");
//        } catch (RPNException rpnEx) {
//            assertThat(rpnEx.getMessage(), equalTo("Divisor cannot be zero."));
//        }
//        BigDecimal[] stackArray = calc.getOperandStackArray();
//        Action[] recordArray = calc.getOperationHistoryArray();
//        assertThat(stackArray.length, is(6));
//        assertThat(recordArray.length, is(6));
//        assertThat(stackArray[5], comparesEqualTo(BigDecimal.ZERO));
//        assertThat(stackArray[4], equalTo(new BigDecimal("6")));
//    }
//
//    @Test
//    public void testNegativeSqrt() throws Exception{
//        //keep the stack and the history
//        Calculator calc = new Calculator(8);
//        try {
//            calc.processInput("5 4 3 2 6 22 -1  * sqrt 2 3 4 ");
//            fail("expected");
//        } catch (RPNException rpnEx) {
//            assertThat(rpnEx.getMessage(), equalTo("A negative value -22 cannot perform a square root."));
//        }
//        BigDecimal[] stackArray = calc.getOperandStackArray();
//        Action[] recordArray = calc.getOperationHistoryArray();
//        assertThat(stackArray.length, is(6));
//        assertThat(stackArray[5], equalTo(new BigDecimal("-22")));
//        assertThat(recordArray.length, is(8));
//        assertThat(recordArray[0],instanceOf(Calculator.BinaryRPNActionRecord.class));
//
//        calc.processInput("undo");
//        stackArray=calc.getOperandStackArray();
//        recordArray=calc.getOperationHistoryArray();
//        assertThat(stackArray[6],equalTo(new BigDecimal(-1)));
//        assertThat(stackArray[5],equalTo(new BigDecimal(22)));
//        assertThat(stackArray.length,is(7));
//        assertThat(recordArray.length,is(7));
//    }
//
//    @Test
//    public void printStack() throws Exception {
//        Calculator calc = new Calculator(8);
//        calc.processInput("5.58209749445923078164062862   4.482337867831652712019091 3.14159265358979323846264338 2.327950288419716939937510");
//        calc.processInput("1.000 2.1000 3.20000000     0.0000");
//        assertThat(calc.printStack(), equalTo("Stack: 5.5820974945 4.4823378678 3.1415926536 2.3279502884 1 2.1 3.2 0"));
//    }
//
//
//}