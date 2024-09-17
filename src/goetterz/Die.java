/*
 * Course: CSC1020
 * Homework 2 - File IO
 * goetterz.Driver
 * Name: Zak Goetter
 * Last Updated: 9/11/2024
 */

package goetterz;

import java.util.Random;

/**
 * This is a class that controls and contains methods related to Die
 * @author Zak Goetter
 */
public class Die {

    /**
     * This int holds the minimum number of sides
     */
    public static final int MIN_SIDES = 2;
    /**
     * This int holds the maximum number of sides
     */
    public static final int MAX_SIDES = 100;
    private int currentValue;
    private final int numSides;
    private final Random random = new Random();


    /**
     * This creates an object called Die
     * @param numSides - takes in the number of sides on the die
     * @throws IllegalArgumentException - Input Incorrect: Wrong amount of sides.
     */
    public Die(int numSides) {
        this.numSides = numSides;
        if(numSides > MAX_SIDES || numSides < MIN_SIDES){
            throw new IllegalArgumentException("Input Incorrect: Wrong amount of sides.");
        }

    }

    /**
     * This method returns the current value of the Die
     * @return  - returns the current value of the Die
     * @throws DieNotRolledException - Dice has not been rolled
     */
    public int getCurrentValue() {
        if (this.currentValue == 0) {
            throw new DieNotRolledException("Dice has not been rolled");
        } else {
            int curr = currentValue;
            currentValue = 0;
            return curr;
        }
    }

    /**
     * This method rolls the Die and sets the current value to the rolled number
     */
    public void roll() {
        this.currentValue = random.nextInt(this.numSides) + 1;
    }
}
