package assignment3;

import java.lang.Math;

import java.util.HashSet;
import java.util.HashMap;

/* Herein, we specify static methods shared across the classes... */

public class Utils
{   
    // TODO: add comments to explain the use of Collections, etc.

    /* TODO: according to some sources, this approach might be a bit unsafe
       and also quite memory consuming. Ask the teacher about this. */

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
    
    /** 
     * @param id
     * @throws Exception
     */
    public static void checkEmptyId(String id) throws Exception {
        if (id.trim().isEmpty())
            throw new Exception("ID cannot be blank.");
    }
    
    /** 
     * @param name
     * @throws Exception
     */
    public static void checkEmptyName(String name) throws Exception {
        if (name.trim().isEmpty())
            throw new Exception("Name cannot be blank.");
    }

    /** 
     * @param salary
     * @throws Exception
     */
    public static void checkEmptyGrossSalary(double salary) throws Exception {
        if (salary <= 0.0) {
            throw new Exception("Salary must be greater than zero.");
        }
    }
    
    /** 
     * @param degree
     * @throws Exception
     */
    public static void checkValidDegree(String degree) throws Exception {
        // int counter = 0;
        // for (Degrees currentDegree : Degrees.values()) {
        //     if (!currentDegree.name().equals(degree)) 
        //         counter++;
        // }

        // if (counter == 3) {
        //     throw new Exception("Degree must be one of the options: BSc, MSc or PhD.");
        // }

        if (!DEGREES.keySet().contains(degree)) {
            throw new Exception("Degree must be one of the options: BSc, MSc or PhD.");
        }
    } 
}