/***************************************************************************************************
 * Group Work Assignment 3 - A3-Group 20
 * File: StringCannotBeEmpty.java
 * Members: Ionel Pop, Joel Mattsson, Michal Spano
 * For DIT043: Object Oriented Programming; SEM@GU.
 ***************************************************************************************************/

package assignment3.CustomExceptions;

public class StringCannotBeEmptyException extends Exception {
        
    public StringCannotBeEmptyException()
    {
        // default Exception message
        super("String cannot be empty.");
    }

    public StringCannotBeEmptyException(String message)
    {
        super(message);
    }
}