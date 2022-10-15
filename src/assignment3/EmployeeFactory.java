/***************************************************************************************************
 * Group Work Assignment 3 - A3-Group 20
 * File: EmployeeFactory.java
 * Members: Ionel Pop, Joel Mattsson, Michal Spano
 * For DIT043: Object Oriented Programming; SEM@GU.
 ***************************************************************************************************/

package assignment3;

/* Explanation:
 * We would like to separate the creation of the individual objects from the Company.java class, hence
 * we create EmployeeFactory.java. This class will be responsible for creating the objects, and will
 * be called from Company.java. */

public class EmployeeFactory 
{
     private EmployeeFactory() 
     {
      /* unreachable constructor; this class is not meant to be instantiated
       * all the methods are static, so we don't need to instantiate it */
     }

    /**
     * @param employeeID
     * @param employeeName
     * @param grossSalary
     * @return Employee
     * @throws Exception
     */
     public static Employee createEmployee(String employeeID, String employeeName, double grossSalary) throws Exception
     {
         return new Employee(employeeID, employeeName, grossSalary);
     }
     
     /** 
      * @param employeeID
      * @param employeeName
      * @param grossSalary
      * @param degree
      * @return Manager
      * @throws Exception
      */
     public static Manager createManager(String employeeID, String employeeName, double grossSalary, String degree) throws Exception 
     {
        return new Manager(employeeID, employeeName, grossSalary, degree);
     }
     
     /** 
      * @param employeeID
      * @param employeeName
      * @param grossSalary
      * @param degree
      * @param department
      * @return Director
      * @throws Exception
      */
     public static Director createDirector(String employeeID, String employeeName, double grossSalary, String degree, String department) throws Exception 
     {
        return new Director(employeeID, employeeName, grossSalary, degree, department);
     }
     
     /** 
      * @param employeeID
      * @param employeeName
      * @param grossSalary
      * @param gpa
      * @return Intern
      * @throws Exception
      */
     public static Intern createIntern(String employeeID, String employeeName, double grossSalary, int gpa) throws Exception 
     {
        return new Intern(employeeID, employeeName, grossSalary, gpa);
     }
}