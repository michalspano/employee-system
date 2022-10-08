package assignment3;

import java.lang.Math;

/* Herein, we specify static methods shared across the classes... */

public class Utils
{
    /** truncate a double to n decimal places
     * @param value
     * @param decimalPlaces
     * @return double
     */
    public static double truncateDouble(double value, int decimalPlaces) {
        double powerOfTen = Math.pow(10, decimalPlaces);
        return Math.floor(value * powerOfTen) / powerOfTen;
    }
}