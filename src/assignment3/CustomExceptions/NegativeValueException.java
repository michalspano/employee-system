/***************************************************************************************************
 * Group Work Assignment 3 - A3-Group 20
 * File: NegativeValueException.java
 * Members: Ionel Pop, Joel Mattsson, Michal Spano
 * For DIT043: Object Oriented Programming; SEM@GU.
 ***************************************************************************************************/

package assignment3.CustomExceptions;

public class NegativeValueException extends Exception {

    public NegativeValueException() {
        super("Value must be greater than zero");
    }

    public NegativeValueException(String message) {
        super(message);
    }
}