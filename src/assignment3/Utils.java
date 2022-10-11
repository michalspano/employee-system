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

    public static void checkValidDegree(String degree) throws Exception {
        for (Degrees currentDegree : Degrees.values()) {
            if (currentDegree.name().equals(degree)) 
                throw new Exception("Degree must be one of the options: PhD, MSc or PhD.");
        }
    } 
}