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

/* Herein, we specify static methods shared across the classes... */

public class Utils
{   
    private Utils() 
    {
        /* unreachable constructor; this class is not meant to be instantiated
         * all the methods are static, so we don't need to instantiate it */
    }

    // TODO: add comments to explain the use of Collections, etc.

    /* 'Double Brace Initialization' (add() method one-liner)
     * Documentation summary via:
     * https://www.geeksforgeeks.org/double-brace-initialization-java/
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

    /** truncate a double to n decimal places
     * @param value
     * @param decimalPlaces
     * @return double
     */
    public static double truncateDouble(double value, int decimalPlaces) {
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
    public static boolean isStringEmpty(String string) {
        return string == null || string.trim().isEmpty();
    }
}