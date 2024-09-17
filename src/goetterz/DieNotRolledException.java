/*
 * Course: CSC1020
 * Homework 2 - File IO
 * goetterz.Driver
 * Name: Zak Goetter
 * Last Updated: 9/13/2024
 */

package goetterz;

/**
 *This class is to create a custom Exception
 * @author Zak Goetter
 */
public class DieNotRolledException extends RuntimeException {

    /**
     * Sets up the message for my custom DieNotRolledException
     * @param message - takes in the message I want to set to the exception
     */
    public DieNotRolledException(String message) {
        super(message);
    }

    /**
     * This method gets the message that I want to return from my custom Exception
     * @return - returns the message of my custom Exception
     */
    public String getMessage() {
        return super.getMessage();
    }

}
