/***************************************************************************************************
 * Group Work Assignment 3 - A3-Group 20
 * File: CompanyUtils.java
 * Members: Ionel Pop, Joel Mattsson, Michal Spano
 * For DIT043: Object Oriented Programming; SEM@GU.
 ***************************************************************************************************/

package assignment3;

// static utilities specific to Company.java

public class CompanyUtils {
    /**
     * @param id
     * @return String
     */
    public static String registeredEmployee(String id) {
        return String.format("Employee %s was registered successfully.", id);
    }

    /**
     * @param id
     * @return String
     */
    public static String updatedEmployee(String id) {
        return String.format("Employee %s was updated successfully", id);
    }

    /**
     * @param id
     * @param newType
     * @return String
     */
    public static String promotedEmployee(String id, String newType) {
        return String.format("%s promoted successfully to %s.", id, newType);
    }
}