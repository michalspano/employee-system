package assignment3;

public class Intern extends Employee
{
    private int gpa;
    private final double BONUS = 1_000;

    public Intern(String ID, String name, double grossSalary, int gpa) throws Exception {
        super(ID, name, grossSalary);

        checkValidGPA(gpa);

        this.gpa = gpa;
    }
    
    /** 
     * @param newGPA
     */
    public void setGpa(int newGPA) throws Exception
    {
        checkValidGPA(newGPA);

        this.gpa = newGPA;
    }
    
    /** Calculate the gross salary based on the GPA of the person
     * @return double
     */
    @Override
    public double getGrossSalary() {
        if (this.gpa <= 5) {
            return 0;
        }
        else if (this.gpa <= 8) {
            return super.getGrossSalary();
        }
        
        return super.getGrossSalary() + BONUS;
    }
    
    /** For an Intern, the gross salary equals to the net salary
     * @return double
     */
    @Override
    public double getNetSalary() {
        return this.getGrossSalary();
    }

    public static void checkValidGPA(int newGPA) throws Exception
    {
        if(newGPA < 0 || newGPA > 10)
        {
            String message = String.format("%d outside range. Must be between 0-10.", newGPA);
            throw new Exception(message);
        }
    }
    
    /** 
     * @return String
     */
    @Override
    public String toString() {
        return String.format("%s's gross salary is %.2f SEK per month. GPA: %d", 
                            this.getName(), this.getGrossSalary(), this.gpa);
    }
}