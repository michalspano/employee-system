/***************************************************************************************************
 * Group Work Assignment 3 - A3-Group 20
 * File: Employee.java
 * Members: Ionel Pop, Joel Mattsson, Michal Spano
 * For DIT043: Object Oriented Programming; SEM@GU.
 ***************************************************************************************************/

package assignment3;

public class Employee implements Comparable<Employee>
{
    private final String ID;
    private String name;
    private double grossSalary;

    public Employee(String ID, String name, double grossSalary) throws Exception 
    {
        /* these need to be checked before the creation of the Employee object via the Constructor
         * if any of these are invalid, an Exception is thrown and no object is created
         * Therefore, we need to check them before the object is created */

        ExceptionThrower.checkEmptyId(ID);                      // ID mustn't be empty
        ExceptionThrower.checkEmptyName(name);                  // name mustn't be empty
        ExceptionThrower.checkEmptyGrossSalary(grossSalary);    // grossSalary must be greater than 0
        
        this.ID = ID;
        this.name = name;
        this.grossSalary = Utils.truncateDouble(grossSalary, 2);
    }

    /** compareTo from Comparable interface
     * @param otherEmployee
     * @return int
     */
    @Override
    public int compareTo(Employee otherEmployee) 
    {
        // store the two compared values inside variables for readability
        double currentSalary = this.getGrossSalary();
        double otherSalary   = otherEmployee.getGrossSalary();

        /* Instead of using an if-else-if-else construct, we use this approach
         * for comparing two doubles, when 1 is returned if the first is greater,
         * -1 if the second is greater and 0 if they are equal.
         * 
         * Docs: https://docs.oracle.com/javase/8/docs/api/java/lang/Double.html [compare() method]
         * Last accessed: 12.10.2022 */

        return Double.compare(currentSalary, otherSalary);
    }
    
    /* Explanation:
     * even though that getGrossSalary() and getRawSalary() appear identical here, there's a major difference.
     * getGrossSalary() is overridden in each of the subclasses (in order to fit the requirements of the assignment)
     * whilst getRawSalary() is not. This is because the raw salary is the same for all subclasses, and therefore
     * the promotion process is possible (we need to know the salary of the employee before the promotion). 
     * A workaround would be to create an Interface with this method only (getRawSalary()) and implement it in each
     * sub-class, but that would be more repetitive. */

    /**
     * @return double
     */
    public double getGrossSalary() 
    {
        return this.grossSalary;
    }
    
    /**
     * @return double
     */
    public double getRawSalary() 
    {
        return this.grossSalary;
    }

    /** 
     * @return double
     */
    public double getNetSalary()
    {
        // an employee (by default) pays 10% of his gross salary as taxes
        return Utils.truncateDouble(this.getGrossSalary() * 0.9, 2);
    }
    
    /** 
     * @return String
     */
    public String getID()
    {
        return this.ID;
    }

    /** 
     * @return String
     */
    public String getName()
    {
        return this.name;
    }
    
    /** 
     * @param newName
     * @throws Exception
     */
    public void setName(String newName) throws Exception 
    {
        // ensure that the name is valid
        ExceptionThrower.checkEmptyName(newName);
        this.name = newName;
    }
    
    /** 
     * @param newGrossSalary
     * @throws Exception
     */
    public void setGrossSalary(double newGrossSalary) throws Exception 
    {
        // ensure that the given gross salary is non-negative & positive
        ExceptionThrower.checkEmptyGrossSalary(newGrossSalary);
        this.grossSalary = newGrossSalary;
    }
    
    /** Two employees are equal if they have the same ID
     * @param otherEmployee
     * @return boolean
     */
    public boolean equals(Object otherObject) 
    {
        if (otherObject == null) return false; // don't compare with null

        if (otherObject instanceof Employee) 
        {
            // perform down-casting to access the ID
            Employee otherEmployee = (Employee) otherObject;
            return this.ID.equals(otherEmployee.getID());
        }

        // otherwise, return false
        return false;
    }

    /** 
     * @return String
     */
    public String toString() 
    {
        return String.format("%s's gross salary is %.2f SEK per month.", 
                            this.name, this.grossSalary);
    }
}