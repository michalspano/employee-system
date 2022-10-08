package assignment3;

public class Employee
{
    private final String ID;
    private String name;
    private double grossSalary;

    public Employee(String ID, String name, double grossSalary) {
        this.ID = ID;
        this.name = name;
        this.grossSalary = Utils.truncateDouble(grossSalary, 2);
    }
    
    /** 
     * @return double
     */
    public double getNetSalary()
    {
        return Utils.truncateDouble(this.getGrossSalary() - (this.getGrossSalary() * 0.1), 2);
    }
    
    /** 
     * @return String
     */
    public String getID()
    {
        return this.ID;
    }

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

    public void setName(String newName) {
        this.name = newName;
    }
    
    /** 
     * @param newGrossSalary
     */
    public void setGrossSalary(double newGrossSalary) {
        this.grossSalary = newGrossSalary;
    }
    
    public boolean equals(Employee otherEmployee) {
        return this.ID.equals(otherEmployee.ID);
    }

    /** 
     * @return String
     */
    public String toString() {
        return String.format("%s's gross salary is %.2f SEK per month.", 
                            this.name, this.grossSalary);
    }
}