/***************************************************************************************************
 * Group Work Assignment 3 - A3-Group 20
 * File: Intern.java
 * Members: Ionel Pop, Joel Mattsson, Michal Spano
 * For DIT043: Object Oriented Programming; SEM@GU.
 ***************************************************************************************************/

package assignment3;

public class Intern extends Employee
{
    private final double BONUS = 1_000;
    private int gpa;

    public Intern(String ID, String name, double grossSalary, int gpa) throws Exception 
    {
        /* Analysis:
         * In the creation of the Intern object, we ensure that the conditions are met (regarding the super-class)
         * via the super() constructor, then an additional check for the valid gpa is performed. In case any exception 
         * is thrown, the creation of the object is aborted. */
        
        super(ID, name, grossSalary);
        ExceptionThrower.checkValidGPA(gpa);
        this.gpa = gpa;
    }
    
    /** 
     * @param newGPA
     */
    public void setGpa(int newGPA) throws Exception
    {
        // ensure that the new gpa is valid
        ExceptionThrower.checkValidGPA(newGPA);
        this.gpa = newGPA;
    }
    
    /** Calculate the gross salary based on the GPA of the person
     * @return double
     */
    @Override
    public double getGrossSalary()
    {
        if (this.gpa <= 5) 
        {
            return 0;
        }
        
        else if (this.gpa <= 8) 
        {
            return super.getGrossSalary();
        }
    
        return super.getGrossSalary() + BONUS;
    }
    
    /** For an Intern, the gross salary equals to the net salary
     * @return double
     */
    @Override
    public double getNetSalary() 
    {
        return this.getGrossSalary();
    }
    
    /** 
     * @return String
     */
    @Override
    public String toString()
    {
        return String.format("%s's gross salary is %.2f SEK per month. GPA: %d", 
                            this.getName(), this.getGrossSalary(), this.gpa);
    }
}