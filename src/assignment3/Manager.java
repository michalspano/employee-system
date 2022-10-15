/***************************************************************************************************
 * Group Work Assignment 3 - A3-Group 20
 * File: Manager.java
 * Members: Ionel Pop, Joel Mattsson, Michal Spano
 * For DIT043: Object Oriented Programming; SEM@GU.
 ***************************************************************************************************/

package assignment3;

public class Manager extends Employee 
{
    private String degree;
    
    public Manager(String ID, String name, double grossSalary, String degree) throws Exception 
    {   
        /* Analysis:
         * In the creation of the Manager object, we ensure that the conditions are met (regarding the super-class)
         * via the super() constructor, then an additional check for the valid degree is performed. In case 
         * any exception is thrown, the creation of the object is aborted. */
        
        super(ID, name, grossSalary);       
        ExceptionThrower.checkValidDegree(degree);     
        this.degree = degree;               
    }
    
    /** 
     * @param newDegree
     * @throws Exception
     */
    public void updateDegree(String newDegree) throws Exception
    {
        // ensure that the given degree valid
        ExceptionThrower.checkValidDegree(newDegree);
        this.degree = newDegree;
    }
    
    /** 
     * @return String
     */
    public String getDegree()
    {
        return this.degree;
    }
    
    /** 
     * @return double
     */
    @Override
    public double getGrossSalary()
    {
        /* Analysis:
         * Since we are adding a bonus percentage to the original GrossSalary, we can
         * add 1 to the bonus to get the final result. */

        double bonus = Utils.DEGREES.get(this.degree);
        return Utils.truncateDouble(super.getGrossSalary() * (bonus + 1), 2);
    }
    
    /** 
     * @return String
     */
    @Override
    public String toString() 
    {
        return String.format("%s %s's gross salary is %.2f SEK per month.",
                            this.degree, super.getName(), this.getGrossSalary());
    }
}