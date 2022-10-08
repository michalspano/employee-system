package assignment3;

import java.util.ArrayList;
// import java.util.Map;

// TODO: consider handling an invalid id, hence '-1' from 'findEmployeeIndex(...)'

public class Company {

    private static final String EOL = System.lineSeparator();
    
    /* Collection of type ArrayList to store the current employees of the company */
    private ArrayList<Employee> employees;

    public Company()
    {
        this.employees = new ArrayList<>();
    }

    /* Below, we list the different overloaded methods to create different types of Employees per the specifications */
    
    /** 
     * @param employeeID
     * @param employeeName
     * @param grossSalary
     * @return String
     */
    public String createEmployee(String employeeID, String employeeName, double grossSalary) {
        
        Employee newEmployee = new Employee(employeeID, employeeName, grossSalary);
        this.employees.add(newEmployee);
        
        return String.format("Employee %s was registered successfully.", employeeID);
    }
    
    /** 
     * @param employeeID
     * @param employeeName
     * @param grossSalary
     * @param degree
     * @return String
     */
    public String createEmployee(String employeeID, String employeeName, double grossSalary, String degree) {

        Employee newEmployee = new Manager(employeeID, employeeName, grossSalary, degree);
        this.employees.add(newEmployee);

        return String.format("Employee %s was registered successfully.", employeeID);
    }
    
    /** 
     * @param employeeID
     * @param employeeName
     * @param grossSalary
     * @param degree
     * @param department
     * @return String
     */
    public String createEmployee(String employeeID, String employeeName, double grossSalary, String degree, String department) {

        Employee newEmployee = new Director(employeeID, employeeName, grossSalary, degree, department);
        this.employees.add(newEmployee);

        return String.format("Employee %s was registered successfully.", employeeID);
    }
    
    /** 
     * @param employeeID
     * @param employeeName
     * @param grossSalary
     * @param gpa
     * @return String
     */
    public String createEmployee(String employeeID, String employeeName, double grossSalary, int gpa) {

        Employee newEmployee = new Intern(employeeID, employeeName, grossSalary, gpa);
        this.employees.add(newEmployee);

        return String.format("Employee %s was registered successfully.", employeeID);
    }
    
    /** 
     * @param id
     */
    public String removeEmployee(String id)
    {
        int employeeIndex = findEmployeeIndex(id);

        if (employeeIndex == -1) return "";

        this.employees.remove(employeeIndex);

        return String.format("Employee %s was successfully removed.", id);
    }
    
    /** 
     * @param id
     * @return String
     */
    public String printEmployee(String id)
    {
        int employeeIndex = findEmployeeIndex(id);

        Employee currentEmployee = this.employees.get(employeeIndex);

        return currentEmployee.toString();
    }
    
    /** 
     * @return String
     */
    public String printAllEmployees()
    {
        String msg = "All registered employees:" + EOL;

        for (Employee employee : this.employees) {
            msg += employee.toString() + EOL;
        }
    
        return msg;
    }
    
    /** 
     * @return String
     */
    public String printSortedEmployees() {

        /* TODO: Adduce explanation of the following code, regarding the complexity, structure, choice of algorithm, etc. */

        String result = "Employees sorted by gross salary (ascending order):" + EOL;

        Employee[] employees = new Employee[this.employees.size()];

        for (int i = 0; i < this.employees.size(); i++) {
            employees[i] = this.employees.get(i);
        }

        // sort the array
        for (int i = 0; i < employees.length; i++) {
            for (int j = 0; j < employees.length - i - 1; j++) {
                
                // compare the two consecutive values of the array
                double cursorValue = employees[j].getGrossSalary();
                double nextValue = employees[j + 1].getGrossSalary();
                
                if (cursorValue > nextValue) {
                    
                    // Swap the two Objects inside the array
                    Employee temp = employees[j];
                    employees[j] = employees[j + 1];
                    employees[j + 1] = temp;
                }
            }
        }

        for (Employee employee : employees) {
            result = result + employee.toString() + EOL;
        }

        return result;
    }

    /** 
     * @param id
     * @return double
     */
    public double getNetSalary(String id) {
        int employeeIndex = findEmployeeIndex(id);

        Employee currentEmployee = this.employees.get(employeeIndex);
        
        return currentEmployee.getNetSalary();
    }

    /* The following methods for updating the attributes are quite repetitive, consider making them more cohesive */
    
    /** 
     * @param id
     * @param newName
     */
    public String updateEmployeeName(String id, String newName) {
        int employeeIndex = findEmployeeIndex(id);

        this.employees.get(employeeIndex).setName(newName);

        return String.format("Employee %s was updated successfully", id);
    }
    
    /** 
     * @param id
     * @param newGpa
     * @return String
     */
    public String updateInternGPA(String id, int newGpa)
    {
        int employeeIndex = findEmployeeIndex(id);

        Employee currentEmployee = this.employees.get(employeeIndex);

        if (!(currentEmployee instanceof Intern)) return "";

        Intern internEmployee = (Intern) currentEmployee;
        internEmployee.setGpa(newGpa);

        return String.format("Employee %s was updated successfully", id);

    }

    
    /** 
     * @param id
     * @param newDegree
     * @return String
     */
    public String updateManagerDegree(String id, String newDegree)
    {
        int employeeIndex = findEmployeeIndex(id);

        Employee currentEmployee = this.employees.get(employeeIndex);

        if (!(currentEmployee instanceof Manager)) return "";

        Manager managerEmployee = (Manager) currentEmployee;
        managerEmployee.updateDegree(newDegree);

        return String.format("Employee %s was updated successfully", id);   
    }
    
    /** 
     * @param id
     * @param newDept
     * @return String
     */
    public String updateDirectorDept(String id, String newDept)
    {
        int employeeIndex = findEmployeeIndex(id);

        Employee currentEmployee = this.employees.get(employeeIndex);

        if (!(currentEmployee instanceof Director)) return "";

        Director directorEmployee = (Director) currentEmployee;
        directorEmployee.updateDepartment(newDept);

        return String.format("Employee %s was updated successfully", id);  
    }

    /** 
     * @param id
     * @param newGrossSalary
     * @return String
     */
    public String updateGrossSalary(String id, double newGrossSalary)
    {
        int employeeIndex = findEmployeeIndex(id);

        this.employees.get(employeeIndex).setGrossSalary(newGrossSalary);

        return String.format("Employee %s was updated successfully", id);
    }
    
    // TODO: start adding functionalities here
    // public Map<String, Integer> mapEachDegree()
    // {
    //     Map<String, Integer> degreesMap = new Map<String, Integer>();
        
    // }

    /** 
     * @return int
     */
    public int getNumberOfEmployees() {
        return this.employees.size();
    }

    /** TODO: fix the truncation of this method
     * @return double
     */
    public double getTotalNetSalary() {
        double sum = 0.0;
        
        for(Employee employee: this.employees)
        {
            sum += employee.getNetSalary();   
        }
        
        return sum;
    }

    /***************** Utilities *****************/
    
    private int findEmployeeIndex(String id) {
        for (int i = 0; i < employees.size(); i++) {
            Employee currentEmployee = employees.get(i);

            if (currentEmployee.getID().equals(id)) {
                return i;
            }
        }

        return -1;
    }
}