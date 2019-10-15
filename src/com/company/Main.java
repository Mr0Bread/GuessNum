package com.company;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class Main {

    static Random rand = new Random();
    static Scanner scan = new Scanner(System.in);
    static ArrayList<GameResult> leaderBoard = new ArrayList<>();

    public static void main(String[] args) {

        loadResults();

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
                        r.setName(userName);
                        r.setTriesCount(i + 1);
                        long t2 = System.currentTimeMillis();
                        r.setTime(t2 - t1);
                        leaderBoard.add(r);
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

        if (!ans) {
            System.out.println("Goodbye!");
        }

        showResults2();

        printInFile();

    }

    private static void loadResults() {
        File file = new File("Statistics.txt");
        try (Scanner in = new Scanner(file)) {
            while (in.hasNext()) {
                GameResult r = new GameResult();

                String name = in.next();
                int tries = in.nextInt();
                long time = in.nextInt();

                r.setName(name);
                r.setTriesCount(tries);
                r.setTime(time);

                leaderBoard.add(r);
            }
        } catch (IOException e) {
            System.out.println("Can't read file!!!");
        }
    }


    private static void showResults2() {
        System.out.println("Statistics:\n");
        leaderBoard.stream().sorted(Comparator.comparing(GameResult::getTriesCount).thenComparing(GameResult::getTime))
                .limit(5)
                .forEach(r -> System.out.print("User name: " + r.getName() + "\nTries count: " + r.getTriesCount() + "\n" + "Play time: " + r.getTime() + " millisecond(s)" + "\n\n"));
    }

    public static void printInFile() {
        File file = new File("Statistics.txt");

        try (PrintWriter out = new PrintWriter(file)) {

            leaderBoard.stream().sorted(Comparator.comparing(GameResult::getTriesCount).thenComparing(GameResult::getTime))
                    .forEach(r -> out.println(r.getName() + " " + r.getTriesCount() + " " + r.getTime()));

        } catch (IOException e) {
            System.out.println("something went wrong!");
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



