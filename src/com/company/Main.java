package com.company;

import java.util.Random;
import java.util.Scanner;

public class Main {
    static Random rand = new Random();
    static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {

        int guessNum = rand.nextInt(100) + 1; // Random Number
        boolean userWon = false;

        System.out.println("A u ready? 1 to Yes and 2 to No");

        int ans = scan.nextInt();
        if (ans == 1) {

            while (ans == 1) {
                for (int i = 0; i < 10; i++) {

                    System.out.println("Enter your number: ");
                    int userNum = scan.nextInt(); // User's Number

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

                System.out.println("One more try? 1 to Yes and 2 to No");
                ans = scan.nextInt();
            }
        }

        if (ans == 2) {
            System.out.println("Goodbye!");
        }
    }

}



