package assignment3;

public class Manager extends Employee {

    private String degree;
    
    public Manager(String ID, String name, double grossSalary, String degree) throws Exception {
        
        super(ID, name, grossSalary);
        
        // Utils.checkValidDegree(degree);

        this.degree = degree;
    }
    
    /** 
     * @param newDegree
     */
    public void updateDegree(String newDegree)
    {
        this.degree = newDegree;
    }

    public String getDegree() {
        return this.degree;
    }
    
    /** 
     * @return double
     */
    @Override
    public double getGrossSalary() {
        double bonus = Degrees.valueOf(this.degree).bonus;

        return Utils.truncateDouble(super.getGrossSalary() * (bonus + 1), 2);
    }
    
    /** 
     * @return String
     */
    @Override
    public String toString() {
        return String.format("%s %s's gross salary is %.2f SEK per month.",
                this.degree, super.getName(), this.getGrossSalary());
    }
}