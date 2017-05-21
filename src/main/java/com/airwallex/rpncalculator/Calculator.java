package com.airwallex.rpncalculator;

import org.apache.log4j.Logger;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.stream.Collectors;

/**
 * Created by wikic on 5/19/2017.
 */
public class Calculator {

    final static Logger logger = Logger.getLogger(Calculator.class);
    private static final char[] BINARY_OPERATORS = new char[]{'+', '-', '*', '/'};
    private static final ArrayList<Operation> BINARY_LAMBDAS;
    private static final String SEPARATOR = "\\s+";
    private static final String TOKEN_QUIT = ":q";
    private static final BigDecimal TWO = new BigDecimal(2);
    private static final int sqrtPrecision = 20;
    private static BigDecimal precE;

    static {
        BINARY_LAMBDAS = new ArrayList<>();
        BINARY_LAMBDAS.add((op1, op2) -> {
            return op1.add(op2);
        });
        BINARY_LAMBDAS.add((op1, op2) -> {
            return op1.subtract(op2);
        });
        BINARY_LAMBDAS.add((op1, op2) -> {
            return op1.multiply(op2);
        });
        BINARY_LAMBDAS.add((op1, op2) -> {
            if (op2.compareTo(BigDecimal.ZERO) == 0) throw new RPNException("Divisor cannot be zero.");
            return op1.divide(op2, 15, BigDecimal.ROUND_HALF_UP);
        });

        String e = "-0.";
        for (int i = 0; i < sqrtPrecision; i++) {
            e += "0";
        }
        e += "1";
        precE = new BigDecimal(e);
    }

    private Deque<BigDecimal> operandStack;
    private Deque<RPNActionRecord> operationHistory;

    public Calculator() {
        this(64);
    }

    public Calculator(int opStackInit) {
        this(opStackInit < 8 ? 8 : opStackInit, opStackInit * 4); //minimum of arrayDeque is 8,  4 is a arbitrary choice
    }

    public Calculator(int opStackInit, int opHistoryInit) {
        operandStack = new ArrayDeque<>(opStackInit);
        operationHistory = new ArrayDeque<>(opHistoryInit);
    }

    /**
     * @param input
     * @return whether to quit the calculator or not
     * @throws RPNException
     */
    public boolean processInput(String input) throws RPNException {
        if (StringUtil.isNullOrEmpty(input)) return false;
        String[] tokens = input.split(SEPARATOR);
        int pos = 1;
        for (String token : tokens) {
            if (StringUtil.isNullOrEmpty(token)) continue;
            if (token.equals(TOKEN_QUIT)) {
                return true;
            }
            processToken(token, pos++);
        }
        return false;
    }

    public String printStack() {
        return operandStack.stream()
                .map(b -> formatBigDecimal(b))
                .collect(Collectors.joining(" ", "Stack: ", ""));
    }

    public String printHistory() {
        return operationHistory.stream()
                .map(a -> a.toString())
                .collect(Collectors.joining(", ", "Histroy: ", ""));
    }

    public BigDecimal[] getOperandStackArray() {
        return operandStack.toArray(new BigDecimal[operandStack.size()]); //bigDecimal is immutable, shallow copy is fine here.
    }

    public RPNActionRecord[] getOperationHistoryArray() {
        return operationHistory.toArray(new RPNActionRecord[operationHistory.size()]);
    }

    private void processToken(String token, int pos) throws RPNException {
        if (token.length() == 1) {
            int opIndex = findOpIndex(token.charAt(0));
            if (opIndex != -1) {
                doBinaryOperation(opIndex, pos);
                return;
            }
        }

        if (token.equals("undo")) {
            undo();
            return;
        }

        if (token.equals("clear")) {
            clearStack();
            return;
        }

        if (token.equals("sqrt")) {
            doSqrt(pos);
            return;
        }

        BigDecimal operand = getBigDecimal(token);
        if (operand != null) {
            operandStack.addLast(operand);
            operationHistory.push(new PushRPNActionRecord());
        }
    }

    private BigDecimal getBigDecimal(String token) throws RPNException {
        BigDecimal result = null;
        try {
            result = new BigDecimal(token);
        } catch (NumberFormatException nfEx) {
            logger.error(String.format("Invald input %s ", token), nfEx);
            throw new RPNException(String.format("Invald input %s ", token));
        }
        return result;
    }

    private void clearStack() {
        Deque<BigDecimal> oldStack = operandStack;
        operandStack = new ArrayDeque<>(64);
        operationHistory.push(new ClearRPNActionRecord(oldStack));
    }

    private void doSqrt(int pos) throws RPNException {
        if (operandStack.isEmpty())
            throw new RPNException(String.format("Operator sqrt (position: %d): insufficient parameters.", pos));
        BigDecimal op = operandStack.removeLast();
        if (op.compareTo(BigDecimal.ZERO) < 0) {
            operandStack.addLast(op);
            throw new RPNException(String.format("A negative value %s cannot perform a square root.", formatBigDecimal(op)));
        }

        BigDecimal result = newtonMethodSqrt2(op);
        operandStack.addLast(result);
        operationHistory.push(new SqrtRPNActionRecord(op));
    }

    public static BigDecimal newtonMethodSqrt2(BigDecimal operand) {
        BigDecimal result = operand;

        while(true) {
            BigDecimal last = result;
            result = result.add(operand.divide(result,20, BigDecimal.ROUND_HALF_UP)).divide(TWO, 20,BigDecimal.ROUND_HALF_UP);
            if(result.add(last.negate()).abs().add(precE).compareTo(BigDecimal.ZERO) < 0)
            break;
        }
        return result;
    }

    private void undo() {
        if (!operationHistory.isEmpty()) {
            RPNActionRecord previousAction = operationHistory.pop();
            previousAction.undo();
        }
    }

    private void doBinaryOperation(int opIndex, int pos) throws RPNException {
        if (operandStack.size() < 2)
            throw new RPNException(String.format("Operator %s (position: %d): insufficient parameters.", BINARY_OPERATORS[opIndex], pos));
        Operation operation = BINARY_LAMBDAS.get(opIndex);
        BigDecimal op2 = operandStack.removeLast();
        BigDecimal op1 = operandStack.removeLast();
        try {

            BigDecimal result = operation.compute(op1, op2);
            operandStack.addLast(result);
            operationHistory.push(new BinaryRPNActionRecord(op1, op2));
        } catch (RPNException rpnEx) {
            operandStack.addLast(op1);
            operandStack.addLast(op2);
            throw rpnEx;
        }
    }

    private int findOpIndex(char c) {
        for (int i = 0; i < BINARY_OPERATORS.length; i++) {
            if (c == BINARY_OPERATORS[i]) return i;
        }
        return -1;
    }

    private String formatBigDecimal(BigDecimal b) {
        return b.setScale(10, RoundingMode.HALF_UP).stripTrailingZeros().toPlainString();
    }

    public class BinaryRPNActionRecord implements RPNActionRecord {

        private BigDecimal op1;
        private BigDecimal op2;

        public BinaryRPNActionRecord(BigDecimal op1, BigDecimal op2) {
            this.op1 = op1;
            this.op2 = op2;
        }

        public BigDecimal getOp1() {
            return op1;
        }

        public BigDecimal getOp2() {
            return op2;
        }

        @Override
        public void undo() {
            if (operandStack.size() == 0) return;
            operandStack.removeLast();
            operandStack.addLast(op1);
            operandStack.addLast(op2);
        }

        @Override
        public String toString() {
            return BinaryRPNActionRecord.class.getSimpleName() + ": " + formatBigDecimal(op1) + " " + formatBigDecimal(op2);
        }
    }

    public class SqrtRPNActionRecord implements RPNActionRecord {

        private BigDecimal op;

        public SqrtRPNActionRecord(BigDecimal op) {
            this.op = op;
        }

        public BigDecimal getOp() {
            return op;
        }

        @Override
        public void undo() {
            if (operandStack.size() == 0) return;
            operandStack.removeLast();
            operandStack.addLast(op);
        }

        @Override
        public String toString() {
            return SqrtRPNActionRecord.class.getSimpleName() + ": " + formatBigDecimal(op);
        }
    }

    public class ClearRPNActionRecord implements RPNActionRecord {

        private Deque<BigDecimal> previousStack;

        public ClearRPNActionRecord(Deque<BigDecimal> previousStack) {
            this.previousStack = previousStack;
        }

        @Override
        public void undo() {
            operandStack = previousStack;
        }

        @Override
        public String toString() {
            return ClearRPNActionRecord.class.getSimpleName();
        }
    }

    public class PushRPNActionRecord implements RPNActionRecord {

        @Override
        public void undo() {
            assert operandStack.size() != 0;
            operandStack.removeLast();
        }

        @Override
        public String toString() {
            return PushRPNActionRecord.class.getSimpleName();
        }
    }
}
