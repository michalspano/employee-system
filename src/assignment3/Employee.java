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
        // TODO: explain the use of all the checks
        Utils.checkEmptyId(ID);
        Utils.checkEmptyName(name);
        Utils.checkEmptyGrossSalary(grossSalary);
        
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

        // sort in the increasing order
        if (currentSalary > otherSalary) 
        {
            return 1;

        } else if (currentSalary == otherSalary) 
        {
            return 0;
        }

        return -1;
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
     * @return double
     */
    public double getGrossSalary()
    {
        return this.grossSalary;
    }
    
    /** 
     * @param newName
     */
    public void setName(String newName) 
    {
        this.name = newName;
    }
    
    /** 
     * @param newGrossSalary
     */
    public void setGrossSalary(double newGrossSalary) 
    {
        this.grossSalary = newGrossSalary;
    }
    
    /** Two employees are equal if they have the same ID
     * @param otherEmployee
     * @return boolean
     */
    public boolean equals(Employee otherEmployee) 
    {
        return this.ID.equals(otherEmployee.ID);
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