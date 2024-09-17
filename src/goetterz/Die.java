/*
 * Course: CSC1020
 * Homework 2 - File IO
 * goetterz.Driver
 * Name: Zak Goetter
 * Last Updated: 9/11/2024
 */

package goetterz;

import java.util.Random;

public class Die {

    public static final int MIN_SIDES = 2;
    public static final int MAX_SIDES = 100;
    private int currentValue;
    private final int numSides;
    private final Random random = new Random();

    public Die(int numSides) {
        this.numSides = numSides;
        if(numSides > MAX_SIDES || numSides < MIN_SIDES){
            throw new IllegalArgumentException("Input Incorrect: Wrong amount of sides.");
        }

    }


    public int getCurrentValue() {
        if (this.currentValue == 0) {
            throw new DieNotRolledException("Dice has not been rolled");
        } else {
            int curr = currentValue;
            currentValue = 0;
            return curr;
        }


    }

    public void roll() {
        this.currentValue = random.nextInt(this.numSides) + 1;
    }
}
