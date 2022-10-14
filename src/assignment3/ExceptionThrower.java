/***************************************************************************************************
 * Group Work Assignment 3 - A3-Group 20
 * File: ExceptionThrower.java
 * Members: Ionel Pop, Joel Mattsson, Michal Spano
 * For DIT043: Object Oriented Programming; SEM@GU.
 ***************************************************************************************************/

package assignment3;

import assignment3.CustomExceptions.*;

public class ExceptionThrower 
{
    private ExceptionThrower() 
    {
      /* unreachable constructor; this class is not meant to be instantiated
       * all the methods are static, so we don't need to instantiate it */
    }

    /**
     * @param id
     * @throws Exception
     */
    public static void checkEmptyId(String id) throws Exception {
        if (Utils.isStringEmpty(id))
            throw new StringCannotBeEmptyException("ID cannot be blank.");
    }

    /**
     * @param name
     * @throws Exception
     */
    public static void checkEmptyName(String name) throws Exception {
        if (Utils.isStringEmpty(name))
            throw new StringCannotBeEmptyException("Name cannot be blank.");
    }

    /**
     * @param salary
     * @throws Exception
     */
    public static void checkEmptyGrossSalary(double salary) throws Exception {
        if (salary <= 0.0) {
            throw new NegativeValueException("Salary must be greater than zero.");
        }
    }

    /**
     * @param degree
     * @throws Exception
     */
    public static void checkValidDegree(String degree) throws Exception {
        if (!Utils.DEGREES.keySet().contains(degree)) {
            throw new Exception("Degree must be one of the options: BSc, MSc or PhD.");
        }
    }

    /**
     * We indicate invalid employee with '-1'
     * 
     * @param id
     * @throws Exception
     */
    public static void checkIfEmployeeFound(String id, int index) throws Exception {
        if (index == -1)
            throw new Exception(String.format("Employee %s was not registered yet.", id));
    }

    /**
     * @param id
     * @throws Exception
     */
    public static void checkIfEmployeeRegistered(String id, int index) throws Exception {
        if (index != -1)
            throw new Exception(String.format("Cannot register. ID %s is already registered.", id));
    }

    /**
     * @throws Exception
     */
    public static void checkIfNoEmployees(int size) throws Exception {
        if (size == 0)
            throw new Exception("No employees registered yet.");
    }

    /** 
     * @param degree
     * @throws Exception
     */
    public static void checkValidDepartment(String degree) throws Exception 
    {
        if (!Utils.DEPARTMENTS.contains(degree)) 
        {
            throw new Exception("Department must be one of the options: Business, Human Resources or Technical.");
        }
    }

    /** 
     * @param newGPA
     * @throws Exception
     */
    public static void checkValidGPA(int newGPA) throws Exception
    {
        if (newGPA < 0 || newGPA > 10)
            throw new Exception(String.format("%d outside range. Must be between 0-10.", newGPA));
    }
}