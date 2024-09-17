/*
 * Course: CSC1020
 * Homework 2 - File IO
 * goetterz.Driver
 * Name: Zak Goetter
 * Last Updated: 9/11/2024
 */

package goetterz;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.OptionalInt;
import java.util.Scanner;
import java.util.stream.IntStream;

/**
 * This is my main class that sets up the different methods needed to run the main
 * @author Zak Goetter
 */
public class Driver {

    /**
     * This int holds the value of the minimum amount of dice allowed
     */
    public static final int MIN_DICE = 2;
    /**
     * This int holds the value of the maximum amount of dice allowed
     */
    public static final int MAX_DICE = 100;


    /**
     * This method gets the Input from the user and checks
     * that they are giving the correct allowed input
     * @return - returns an int array with the users input
     */
    private static int[] getInput() {

        Scanner scan = new Scanner(System.in);

        System.out.println("Please enter the number of dice to roll, " +
                    "how many sides the dice will have, " +
                    "and how many rolls to complete, separating the values by a space.");
        System.out.println("Example: \"2 6 1000\"");
        System.out.println();
        System.out.print("Enter Configuration:");

        String[] stringInput = scan.nextLine().split(" ");
        if(stringInput.length != 3) {
            throw new InputMismatchException("Invalid input; Expected 3 values" +
                        " but only received " + stringInput.length + ".");
        }

        int[] conf = new int[stringInput.length];

        for (int i = 0; i < conf.length; i++) {
            try {
                conf[i] = Integer.parseInt(stringInput[i]);
            } catch (NumberFormatException e) {
                throw new InputMismatchException("Invalid input: " +
                        "All values must be whole numbers.");
            }

        }

        if (conf[1] < Die.MIN_SIDES || conf[1] > Die.MAX_SIDES) {
            throw new InputMismatchException("Bad die creation: " +
                    "Illegal number of sides: " + conf[1] + ".");
        }

        if (conf[0] < MIN_DICE || conf[0] > MAX_DICE) {
            throw new InputMismatchException("Bad die creation: " +
                    "Illegal number of dice: " + conf[1] + ".");
        }

        return conf;

    }


    /**
     * This method creates the Dice
     * @param numDice - takes in the number of dice needed
     * @param numSides - takes in the number of sides on the dice
     * @return - this returns a Die array with the dice set up that was input by the user
     */
    private static Die[] createDice(int numDice, int numSides) {
        Die[] dice = new Die[numDice];

        for (int i = 0; i < numDice; i++) {
            dice[i] = new Die(numSides);
        }

        return dice;
    }


    /**
     * This method rolls all the dice in a Die array
     * @param dice - takes in a Die array
     * @param numSides - takes in the number of sides on the Dice
     * @param numRolls - takes in the number of rolls to do with the Dice
     * @return - returns an int array with the different amounts of each number rolled
     */
    private static int[] rollDice(Die[] dice, int numSides, int numRolls) {
        int[] result = new int[(dice.length * numSides)- dice.length + 1];

        for(int i = 0; i < numRolls; i++) {
            int total = 0;

            for (Die die : dice) {
                die.roll();
                total += die.getCurrentValue();
            }

            result[total - dice.length]++;

        }
        return result;
    }

    /**
     * This method finds the max value in the provided array
     * @param rolls - takes in an int array of rolls
     * @return Max Value
     * @throws NoSuchElementException -- Array is Empty
     */

    private static int findMax(int[] rolls) {
        OptionalInt maxRoll = Arrays.stream(rolls).max();

        if(maxRoll.isPresent()) {
            return maxRoll.getAsInt();
        } else {
            throw new NoSuchElementException("Array is Empty");
        }
    }

    /**
     * This method create the report showing the distribution of dice rolls
     * @param numDice - takes in the number of dice
     * @param rolls - takes in an int array containing the frequencies of each roll
     * @param max - takes in the max value allowed
     */
    private static void report(int numDice, int[] rolls, int max) {
        final int totalRolls = IntStream.of(rolls).sum();
        final int scMult = 10;
        final double sc = max < scMult ?
                (max / (double) totalRolls) :
                (max/ (double) scMult);
        int maxPadLength = (Integer.toString(rolls.length).length())+ 1;
        int maxResultPadLength = Integer.toString(totalRolls).length() + 1;


        for (int i = 0; i < rolls.length; i++) {
            int numleng = (int) Math.floor(Math.log10(i+numDice)) + 1;
            String pad = "";
            String resultPad = "";

            if (numleng < maxPadLength) {
                pad = " ".repeat(maxPadLength-numleng);
            }

            int resultNumLength = rolls[i] != 0 ? (int) Math.floor(Math.log10(rolls[i])) + 1 : 1;
            if (resultNumLength < maxResultPadLength) {
                resultPad = " ".repeat(maxResultPadLength-resultNumLength);
            }

            int rollNumber = i + numDice;
            int starCount = (int) ((rolls[i] / sc));
            String stars = "*".repeat(starCount);

            System.out.printf("%d%s: %d %s%s\n",
                    rollNumber, pad, rolls[i], resultPad, stars);
        }
    }

    /**
     * This is the main method that runs the code
     * @param args - this is part of how the method works
     */
    public static void main(String[] args) {
        int[] conf = new int[0];

        while (conf.length == 0) {
            try {
                conf = getInput();
            } catch (InputMismatchException | NumberFormatException e) {
                System.out.println(e.getMessage());
            }
        }

        int numD = conf[0];
        int numS = conf[1];
        int numR = conf[2];

        Die[] dice = createDice(numD, numS);
        int[] results = rollDice(dice, numS, numR);

        report(numD, results, findMax(results));

    }
}
