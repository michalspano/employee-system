package assignment3;

public class Director extends Manager {
    
    private String department;
    private static final int DIRECTOR_BONUS = 5_000;

    public Director(String ID, String name, double grossSalary, String degree, String department)
    {
        super(ID, name, grossSalary, degree);
        this.department = department;
    }

    /** We apply the bonus of the manager
     * @return double
     */
    @Override
    public double getGrossSalary() {
        return super.getGrossSalary() + DIRECTOR_BONUS;
    }
    
    /** 
     * @param newDept
     */
    public void updateDepartment(String newDept)
    {
        this.department = newDept;
    }
    
    /** TODO: refactor this method for readability
     * @return double
     */
    @Override
    public double getNetSalary()
    {   
        double netSalary = 0;
        double grossSalary = this.getGrossSalary();
        
        if (grossSalary < 30_000) {
            netSalary = super.getNetSalary();
            return netSalary;
        }
        else if(grossSalary < 50_000)
        {
            // 0.2 for 20% Taxes
            double taxes = 0.2;
            return Utils.truncateDouble(this.getGrossSalary() - (this.getGrossSalary() * taxes), 2);

        }
        else if(grossSalary > 50_000) // Maybe just else
        {
            // 0.2 for 20% 30,000 and 40% for the rest
            double taxes1 = 0.2; double taxes2 = 0.4;

            double currentGrossSalary = this.getGrossSalary();

            double reminder = currentGrossSalary - 30_000;

            netSalary = Utils.truncateDouble(30_000 - (30_000 * taxes1), 2) + 
                        Utils.truncateDouble(reminder - (reminder * taxes2), 2);
            
        }
        return netSalary;
    }

    /** 
     * @return String
     */
    @Override
    public String toString()
    {
        return String.format("%s Dept: %s", super.toString(), department);
    }
}