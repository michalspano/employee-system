/***************************************************************************************************
 * Group Work Assignment 3 - A3-Group 20
 * File: CustomExceptions/StringCannotBeEmpty.java
 * Members: Ionel Pop, Joel Mattsson, Michal Spano
 * For DIT043: Object Oriented Programming; SEM@GU.
 ***************************************************************************************************/

package assignment3.CustomExceptions;

public class StringCannotBeEmptyException extends Exception 
{        
    public StringCannotBeEmptyException()
    {
        super("String cannot be empty."); // default message
    }

    public StringCannotBeEmptyException(String message)
    {
        super(message); // user-defined custom message
    }
}