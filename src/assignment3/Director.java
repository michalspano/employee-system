package assignment3;

public class Director extends Manager 
{    
    private String department;
    private static final int DIRECTOR_BONUS = 5_000;

    public Director(String ID, String name, double grossSalary, String degree, String department) throws Exception
    {
        super(ID, name, grossSalary, degree);
        checkValidDepartment(department);
        this.department = department;
    }

    /** We apply the bonus of the manager
     * @return double
     */
    @Override
    public double getGrossSalary() 
    {
        return super.getGrossSalary() + DIRECTOR_BONUS;
    }
    
    /** 
     * @param newDept
     */
    public void updateDepartment(String newDept) throws Exception 
    {
        checkValidDepartment(newDept);
        this.department = newDept;
    }
    
    /**
     * @return double
     */
    @Override
    public double getNetSalary() 
    {   
        double netSalary = 0;
        double grossSalary = this.getGrossSalary();
        
        // 1st case: < 30_000
        if (grossSalary < 30_000) 
        {
            
            netSalary = super.getNetSalary();
            return netSalary;
        }

        /* 2nd case: >= 30_000 and <= 50_000
         * 
         * Analysis:
         * According to the instructions, the employee will be taxed by 20%, therefore:
         * 0.8 ~ 80% of the gross salary. */

        else if(grossSalary <= 50_000) 
        {   
            return Utils.truncateDouble(this.getGrossSalary() * 0.8, 2);

        }
        
        /* 3rd case: > 50_000 
         *
         * Analysis:
         * Since the employee pays 20% of the first 30_000, and then 40% of the remainder,
         * then, taking 20% of the first 30_000 will always have a value of 24_000, and thus
         * no further calculation for it is needed. For the remainder, we apply the specified
         * 40% taxation. */
             
        else if(grossSalary > 50_000) 
        {             
            double currentGrossSalary = this.getGrossSalary();

            double remainder = currentGrossSalary - 30_000;

            netSalary = 24_000 + Utils.truncateDouble(remainder * (0.6), 2);
        }
        return netSalary;
    }
   
    /** 
     * @param degree
     * @throws Exception
     */
    public static void checkValidDepartment(String degree) throws Exception 
    {
        if (!Utils.DEPARTMENTS.contains(degree)) 
        {
            throw new Exception("Department must be one of the options: Business, Human Resources or Technical.");
        }
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