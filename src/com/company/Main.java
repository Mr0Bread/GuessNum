package com.company;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Main {
    static Random rand = new Random();
    static Scanner scan = new Scanner(System.in);
    static ArrayList userNameArray = new ArrayList<String>();

    public static void main(String[] args) {

        String userName = askUserName("Hi, what's your name?");
        System.out.println(userName);

        boolean userWon = false;

        boolean ans = askYesOrNo("Wanna play?");

        if (ans) {

            while (ans) {
                int guessNum = rand.nextInt(100) + 1; // Random Number
                for (int i = 0; i < 10; i++) {

                    int userNum = askInt("Enter your number: ", 1, 100); // User's Number

                    if (userNum == guessNum) {
                        System.out.println("Congrats!");
                        userWon = true;
                        break;

                    } else if (guessNum < userNum) {
                        System.out.println("Your Num is greater!");

                    } else if (guessNum > userNum) {
                        System.out.println("Your num is lesser!");
                    }

                }

                if (!userWon) {
                    System.out.println("U PICKED THE WRONG HOUSE FOOL!");
                }

                ans = askYesOrNo("One more time?");

            }
        }

        if (!ans) {
            System.out.println("Goodbye!");
        }
    }

    static int askInt(String msg, int minNum, int maxNum) { // Ask User to enter his guess

        while (true) {
            try {
                System.out.println(msg);
                int answerInt = scan.nextInt();
                if (answerInt >= minNum && answerInt <= 100) {
                    return answerInt;
                }
            } catch (InputMismatchException ex) {
                System.out.println("It's not a number!");
                scan.next();
            }
            System.out.printf("Please, enter number from %d to %d!\n", minNum, maxNum);
        }
    }

    static boolean askYesOrNo (String askingYesOrNo) { // Check if answer is valid
        while (true) {
            System.out.println(askingYesOrNo);
            String answer = scan.next();

            boolean isYes = answer.equalsIgnoreCase("Yes");
            boolean isNo = answer.equalsIgnoreCase("No");

            if (isYes || isNo) {
                return isYes;
            }

            System.out.println("Just Yes or No");
        }
    }

    static String askUserName (String msg) {
        System.out.println(msg);
        String answer = scan.next();

        if (!userNameArray.contains(answer)) {
            userNameArray.add(answer);
        }

    }
}



