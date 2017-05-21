package com.airwallex.rpncalculator;

import org.apache.log4j.Logger;

import java.util.Scanner;

/**
 * Created by wikic on 5/19/2017.
 */
public class Program {
    final static Logger logger = Logger.getLogger(Calculator.class);

    public static void main(String[] args) {

        Calculator calc = new Calculator(8);
        System.out.println("Welcome to RPNCalculator. Type :'q' to quit.");
        try (Scanner scan = new Scanner(System.in)) {
            while (true) {
                if (scan.hasNext()) {
                    String str1 = scan.nextLine();
                    try {
                        if (calc.processInput(str1)) break;

                    } catch (RPNException e) {
                        System.out.println(e.getMessage());
                    }
                    System.out.println(calc.printStack());
                    //System.out.println(calc.printHistory());
                }
            }
        } catch (Exception ex) {
            logger.error(ex);
            logger.error(calc.printStack());
            logger.error(calc.printHistory());
        }

    }
}

