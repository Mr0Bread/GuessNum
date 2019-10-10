package com.company;

import java.util.*;

public class Main {

    static Random rand = new Random();
    static Scanner scan = new Scanner(System.in);
    static ArrayList<GameResult> userNameArray = new ArrayList<>();

    public static void main(String[] args) {

        boolean userWon = false;

        boolean ans = askYesOrNo("Wanna play?");

        if (ans) {

            while (ans) {
                String userName = askUserName("Hi, what's your name?");

                int guessNum = rand.nextInt(100) + 1;// Random Number
                System.out.println(guessNum);
                long t1 = System.currentTimeMillis();
                for (int i = 0; i < 10; i++) {

                    int userNum = askInt("Enter your number: ", 1, 100); // Users Number

                    if (userNum == guessNum) {
                        System.out.println("Congrats!");
                        GameResult r = new GameResult();
                        r.name = userName;
                        r.triesCount = i + 1;
                        long t2 = System.currentTimeMillis();
                        r.playTime = (t2 - t1) / 1000.0;
                        userNameArray.add(r);
                        userWon = true;
                        break;

                    } else if (guessNum < userNum) {
                        System.out.println("Your Num is greater!");

                    } else {
                        System.out.println("Your num is lesser!");
                    }

                }

                if (!userWon) {
                    System.out.println("U PICKED THE WRONG HOUSE, FOOL!");
                }

                ans = askYesOrNo("One more time?");

            }
        }

        userNameArray.sort(Comparator.comparing(r -> r.triesCount));
        userNameArray.sort(Comparator.comparing(r -> r.playTime));

        System.out.println("Statistics:\n");

        for (GameResult result : userNameArray
        ) {
            System.out.print("User name: " + result.name + "\nTries count: " + result.triesCount + "\n" + "Play time: " + result.playTime + " second(s)" + "\n\n");
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

    static boolean askYesOrNo(String askingYesOrNo) { // Check if answer is valid
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

    static String askUserName(String msg) {
        System.out.println(msg);
        return scan.next();
    }
}



