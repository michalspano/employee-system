/***************************************************************************************************
 * Group Work Assignment 3 - A3-Group 20
 * File: Utils.java
 * Members: Ionel Pop, Joel Mattsson, Michal Spano
 * For DIT043: Object Oriented Programming; SEM@GU.
 ***************************************************************************************************/

package assignment3;

import java.lang.Math;
import java.util.HashSet;
import java.util.HashMap;

/* Here, we specify static methods that may be used across the classes with some essential functionalities 
 * The main reason for this class is, that these methods wouldn't fit the abstractions of the classes they are used in.
 * Instead, they are defined in Utils - a class that is meant to be used as a "toolbox" for the other classes. */

public class Utils
{   
    private Utils() 
    {
        /* unreachable constructor; this class is not meant to be instantiated
         * all the methods are static, so we don't need to instantiate it */
    }

    /* 'Double Brace Initialization' (add() method one-liner)
     * Documentation summary via:
     * https://www.geeksforgeeks.org/double-brace-initialization-java/
     * 
     * This is a more elegant and readable way of initializing a collection. Similarly,
     * we obtain the methods of the classes if this approach is chosen (instead of using,
     * say, an enum) such as the contains() method, etc (that make the code more readable).
     * 
     * Last accessed: 11.10.2022 */
    
    public static final HashSet<String> DEPARTMENTS = new HashSet<>() {{
        add("Business");
        add("Human Resources");
        add("Technical");
    }};

    public static final HashMap<String, Double> DEGREES = new HashMap<>() {{
        put("BSc", 0.1);
        put("MSc", 0.2);
        put("PhD", 0.35);
    }};

    public static final String EOL = System.lineSeparator();

    /** Truncate a double to n decimal places [2]
     * we make this function more general, so it can truncate a decimal to n places.
     * Reason: suppose a customer call, that, all of sudden, all the salaries are truncated
     * to 3 decimal places instead. With this function, we can easily do that.
     * 
     * @param value
     * @param decimalPlaces
     * @return double
     */
    public static double truncateDouble(double value, int decimalPlaces) 
    {
        double powerOfTen = Math.pow(10, decimalPlaces);
        return Math.floor(value * powerOfTen) / powerOfTen;
    }

    /* Analysis:
     * the `String.trim()` method is used to erase all redundant spaces
     * therefore, we cover the corner case when the user enters more single
     * spaces, which is an invalid input. */

    /** 
     * @param string
     * @return boolean
     */
    public static boolean isStringEmpty(String string) 
    {
        return string == null || string.trim().isEmpty();
    }

    // static methods for the Employee class (they return a string literal)

    /**
     * @param id
     * @return String
     */
    public static String registeredEmployee(String id) 
    {
        return String.format("Employee %s was registered successfully.", id);
    }

    /**
     * @param id
     * @return String
     */
    public static String updatedEmployee(String id) 
    {
        return String.format("Employee %s was updated successfully", id);
    }

    /**
     * @param id
     * @param newType
     * @return String
     */
    public static String promotedEmployee(String id, String newType) 
    {
        return String.format("%s promoted successfully to %s.", id, newType);
    }
}