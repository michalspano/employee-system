/***************************************************************************************************
 * Group Work Assignment 3 - A3-Group 20
 * File: EmployeeFactory.java
 * Members: Ionel Pop, Joel Mattsson, Michal Spano
 * For DIT043: Object Oriented Programming; SEM@GU.
 ***************************************************************************************************/

package assignment3;

/* TODO: add explanation of the *static* factory class
 * 
 */

public class EmployeeFactory {
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
      * @param ID
      * @param name
      * @param grossSalary
      * @param degree
      * @return Manager
      * @throws Exception
      */
     public static Manager createEmployee(String ID, String name, double grossSalary, String degree) throws Exception 
     {
        return new Manager(ID, name, grossSalary, degree);
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
     public static Director createEmployee(String employeeID, String employeeName, double grossSalary, String degree, String department) throws Exception 
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
     public static Intern createEmployee(String employeeID, String employeeName, double grossSalary, int gpa) throws Exception {
        return new Intern(employeeID, employeeName, grossSalary, gpa);
     }
}