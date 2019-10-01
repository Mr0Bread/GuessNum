package com.company;

import java.util.Random;
import java.util.Scanner;

public class Main {
    static Random rand = new Random();
    static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {

        // Random Number
        boolean userWon = false;

        String ans = askYesOrNo("Wanna play?");

        if (ans.equals("Yes")) {

            while (ans.equals("Yes")) {
                int guessNum = rand.nextInt(100) + 1;
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

        if (ans.equals("No")) {
            System.out.println("Goodbye!");
        }
    }

    static int askInt(String msg, int minNum, int maxNum) { // Ask User to enter his guess

        while (true) {

            System.out.println(msg);
            int answerInt = scan.nextInt();
            if (answerInt >= minNum && answerInt <= 100) {
                return answerInt;
            }
            System.out.printf("Please, enter number from %d to %d!\n", minNum, maxNum);
        }
    }

    static String askYesOrNo(String askingYesOrNo) { // Check if answer is valid
        System.out.println(askingYesOrNo);
        String answer = scan.next();

        while (!answer.equals("Yes") && !answer.equals("No")) {
            System.out.println("Just Yes or No");
            answer = scan.next();
        }

        return answer;
    }
}



