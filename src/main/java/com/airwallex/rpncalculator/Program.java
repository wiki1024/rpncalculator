package com.airwallex.rpncalculator;

import com.airwallex.rpncalculator.core.*;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Scanner;

/**
 * Created by wikic on 5/19/2017.
 */
public class Program {
    final static Logger logger = Logger.getLogger(Calculator.class);

    public static void main(String[] args) {

        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        Calculator calc =(Calculator) context.getBean("calculator");
        System.out.println("Welcome to RPNCalculator.");
        try (Scanner scan = new Scanner(System.in)) {
            while (true) {
                if (scan.hasNext()) {
                    String str1 = scan.nextLine();
                    try {
                        calc.process(str1);

                    } catch (RPNException e) {
                        System.out.println(e.getMessage());
                    }
                    System.out.println(calc.print());
                }
            }
        } catch (Exception ex) {
            logger.error(ex);
        }

    }
}

