package assignment3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Company 
{    
    /* Collection of type ArrayList to store the current employees of the company */
    private ArrayList<Employee> employees;

    public Company()
    {
        this.employees = new ArrayList<>();
    }

    /* Below, we list the different overloaded methods to create different types of Employees per the specifications */
    
    /** Employee
     * @param employeeID
     * @param employeeName
     * @param grossSalary
     * @return String
     */
    public String createEmployee(String employeeID, String employeeName, double grossSalary) throws Exception 
    {
        checkIfEmployeeExists(employeeID);
        
        Employee newEmployee = new Employee(employeeID, employeeName, grossSalary);

        this.employees.add(newEmployee);
        
        return registerEmployee(employeeID);
    }
    
    /** Manager
     * @param employeeID
     * @param employeeName
     * @param grossSalary
     * @param degree
     * @return String
     */
    public String createEmployee(String employeeID, String employeeName, double grossSalary, String degree) throws Exception 
    {
        checkIfEmployeeExists(employeeID);

        Employee newEmployee = new Manager(employeeID, employeeName, grossSalary, degree);
        this.employees.add(newEmployee);

        return registerEmployee(employeeID);
    }
    
    /** Director
     * @param employeeID
     * @param employeeName
     * @param grossSalary
     * @param degree
     * @param department
     * @return String
     */
    public String createEmployee(String employeeID, String employeeName, double grossSalary, String degree, String department) throws Exception 
    {
        checkIfEmployeeExists(employeeID);

        Employee newEmployee = new Director(employeeID, employeeName, grossSalary, degree, department);
        this.employees.add(newEmployee);

        return registerEmployee(employeeID);
    }
    
    /** Intern
     * @param employeeID
     * @param employeeName
     * @param grossSalary
     * @param gpa
     * @return String
     */
    public String createEmployee(String employeeID, String employeeName, double grossSalary, int gpa) throws Exception 
    {
        checkIfEmployeeExists(employeeID);

        Employee newEmployee = new Intern(employeeID, employeeName, grossSalary, gpa);
        this.employees.add(newEmployee);

        return registerEmployee(employeeID);
    }
    
    /** 
     * @param id
     * @throws Exception
     */
    public String removeEmployee(String id) throws Exception
    {
        int employeeIndex = findEmployeeIndex(id);

        checkIfEmployeeFound(id, employeeIndex);

        this.employees.remove(employeeIndex);

        return String.format("Employee %s was successfully removed.", id);
    }
    
    /** 
     * @param id
     * @return String
     */
    public String printEmployee(String id) throws Exception
    {
        int employeeIndex = findEmployeeIndex(id);

        checkIfEmployeeFound(id, employeeIndex);

        Employee currentEmployee = this.employees.get(employeeIndex);

        return currentEmployee.toString();
    }
    
    /** 
     * @return String
     */
    public String printAllEmployees() throws Exception
    {
        checkIfNoEmployees();

        String msg = "All registered employees:" + Utils.EOL;

        for (Employee employee : this.employees) {
            msg += employee.toString() + Utils.EOL;
        }
    
        return msg;
    }
    
    /** 
     * @return String
     */
    public String printSortedEmployees() throws Exception
    {
        checkIfNoEmployees(); // this needs to be checked before accessing the body of this method

        /* This implementation uses the Collections.sort() from the Collections class; we define the 
         * compareTo() method from the Comparable interface in the Employee class to sort the employees
         * by the grossSalaries in the ascending order. For that, we need to copy the employees from the
         * ArrayList to a new ArrayList, so that we don't modify the original ArrayList. This can be done
         * with the double brace initialization syntax (which greatly improves the readability of the code). */

        /* 'Double Brace Initialization' (further documentation; for adAll() method):
         * Documentation summary via:
         * https://www.techiedelight.com/double-brace-initialization-java/
         * Last accessed: 12.10.2022 */

        ArrayList<Employee> temporaryList = new ArrayList<>() {{ 
            addAll(employees); 
        }}; 

        String result = "Employees sorted by gross salary (ascending order):" + Utils.EOL;

        // sort the temporary list using the Collections.sort() method
        Collections.sort(temporaryList);

        for (Employee employee : temporaryList) {
            result = result + employee.toString() + Utils.EOL;
        }

        return result;
        
        /* 
        String result = "Employees sorted by gross salary (ascending order):" + Utils.EOL;

        Employee[] employees = new Employee[this.employees.size()];

        for (int i = 0; i < this.employees.size(); i++) {
            employees[i] = this.employees.get(i);
        }

        // Ask or discuss with Francisco about the readability of the following code
        // 2/3 think that the for loop it's better for readability

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
        */
    }

    /** 
     * @param id
     * @return double
     */
    public double getNetSalary(String id) throws Exception 
    {
        int employeeIndex = findEmployeeIndex(id);

        checkIfEmployeeFound(id, employeeIndex);

        Employee currentEmployee = this.employees.get(employeeIndex);
        
        return currentEmployee.getNetSalary();
    }

    /* The following methods for updating the attributes are quite repetitive, consider making them more cohesive */
    
    /** 
     * @param id
     * @param newName
     */
    public String updateEmployeeName(String id, String newName) throws Exception
    {

        Utils.checkEmptyName(newName);

        int employeeIndex = findEmployeeIndex(id);

        checkIfEmployeeFound(id, employeeIndex);

        this.employees.get(employeeIndex).setName(newName);

        return updateEmployee(id);
    }
    
    /** 
     * @param id
     * @param newGpa
     * @return String
     */
    public String updateInternGPA(String id, int newGpa) throws Exception
    {
        int employeeIndex = findEmployeeIndex(id);

        checkIfEmployeeFound(id, employeeIndex);

        Employee currentEmployee = this.employees.get(employeeIndex);

        if (!(currentEmployee instanceof Intern)) return "";

        Intern internEmployee = (Intern) currentEmployee;
        internEmployee.setGpa(newGpa);

        return updateEmployee(id);
    }
    
    /** 
     * @param id
     * @param newDegree
     * @return String
     */
    public String updateManagerDegree(String id, String newDegree) throws Exception
    {
        Utils.checkValidDegree(newDegree); // explain why this check is the initial one

        int employeeIndex = findEmployeeIndex(id);
    
        checkIfEmployeeFound(id, employeeIndex);

        Employee currentEmployee = this.employees.get(employeeIndex);

        if (!(currentEmployee instanceof Manager)) return "";

        Manager managerEmployee = (Manager) currentEmployee;
        managerEmployee.updateDegree(newDegree);

        return updateEmployee(id);
    }
    
    /** 
     * @param id
     * @param newDept
     * @return String
     */
    public String updateDirectorDept(String id, String newDept) throws Exception
    {
        int employeeIndex = findEmployeeIndex(id);

        checkIfEmployeeFound(id, employeeIndex);

        Employee currentEmployee = this.employees.get(employeeIndex);

        if (!(currentEmployee instanceof Director)) return "";

        Director directorEmployee = (Director) currentEmployee;
        directorEmployee.updateDepartment(newDept);

        return updateEmployee(id);
    }

    /** 
     * @param id
     * @param newGrossSalary
     * @return String
     */
    public String updateGrossSalary(String id, double newGrossSalary) throws Exception
    {
        Utils.checkEmptyGrossSalary(newGrossSalary);
        
        int employeeIndex = findEmployeeIndex(id);
    
        checkIfEmployeeFound(id, employeeIndex);

        this.employees.get(employeeIndex).setGrossSalary(newGrossSalary);

        return updateEmployee(id);
    }

    /**
     * @return int
     */
    public int getNumberOfEmployees() {
        return this.employees.size();
    }

    /**
     * @return double
     */
    public double getTotalNetSalary() throws Exception
    {
        checkIfNoEmployees();

        double sum = 0.0;

        for (Employee employee : this.employees) {
            sum += employee.getNetSalary();
        }

        return sum;
    }
    
    /* TODO: this method should return String in the specifications
    * If there are no employees registered with a specific degree, the
    * corresponding row is simply not printed. */
    
    /** 
     * @return HashMap<String, Integer>
     */
    public HashMap<String, Integer> mapEachDegree() throws Exception
    {
        checkIfNoEmployees();

        HashMap<String, Integer> degreesMap = new HashMap<String, Integer>();

        for (Employee currentEmployee : this.employees)
        {
            if (currentEmployee instanceof Manager)
            {
                Manager newManager = (Manager) currentEmployee;
                String currentDegree = newManager.getDegree();

                if (!degreesMap.containsKey(currentDegree)) {
                    degreesMap.put(currentDegree, 0);
                }

                int currentDegreeCounter = degreesMap.get(currentDegree);
                degreesMap.put(currentDegree, currentDegreeCounter + 1);
            }
        }
        return degreesMap;
    }

    /******************* PROMOTION *******************/

    /* ArrayList.set();
     * docs: https://docs.oracle.com/javase/7/docs/api/java/util/ArrayList.html
     * 
     * set(int index, E element)
     * Replaces the element at the specified position in this list with the
     * specified element.
     * 
     * Last accessed: 10.10.2022 */

    // facade.promoteToManager(damonID, "PhD")
    public String promoteToManager(String id, String degree) throws Exception
    {
        int employeeIndex = findEmployeeIndex(id);
        checkIfEmployeeFound(id, employeeIndex);

        Employee currentEmployee = this.employees.get(employeeIndex);

        Manager newManager = new Manager(currentEmployee.getID(), 
                                        currentEmployee.getName(), 
                                        currentEmployee.getRawSalary(), 
                                        degree);

        this.employees.set(employeeIndex, newManager);
        return promoteEmployee(id, "Manager");
    }
    
    /** 
     * @param id
     * @param degree
     * @param department
     * @return String
     */
    public String promoteToDirector(String id, String degree, String department) throws Exception
    {

        int employeeIndex = findEmployeeIndex(id);

        checkIfEmployeeFound(id, employeeIndex);

        Employee currentEmployee = this.employees.get(employeeIndex);

        Director newDirector = new Director(currentEmployee.getID(), 
                                        currentEmployee.getName(), 
                                        currentEmployee.getRawSalary(), 
                                        degree, department);
        
        this.employees.set(employeeIndex, newDirector);
        return promoteEmployee(id, "Director");
    }
    
    /** 
     * @param id
     * @param gpa
     * @return String
     */
    public String promoteToIntern(String id, int gpa) throws Exception
    {
        
        int employeeIndex = findEmployeeIndex(id);

        checkIfEmployeeFound(id, employeeIndex);

        Employee currentEmployee = this.employees.get(employeeIndex);

        Intern newDirector = new Intern(currentEmployee.getID(), 
                                        currentEmployee.getName(), 
                                        currentEmployee.getRawSalary(), 
                                        gpa);
        
        this.employees.set(employeeIndex, newDirector);
        return promoteEmployee(id, "Intern");
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
    
    /** 
     * @throws Exception
     */
    // create a method that will throw an exception if the employee is not found
    private void checkIfNoEmployees() throws Exception {
        if (this.employees.isEmpty()) 
            throw new Exception("No employees registered yet.");    
    }
    
    /** 
     * @param id
     * @throws Exception
     */
    private void checkIfEmployeeFound(String id, int index) throws Exception {
        if (index == -1)
            throw new Exception(String.format("Employee %s was not registered yet.", id));
    }
    
    // these methods will be put to a designated Utils.class since they don't depend on this class
    
    /** 
     * @param id
     * @throws Exception
     */
    private void checkIfEmployeeExists(String id) throws Exception {
        if (findEmployeeIndex(id) != -1) 
            throw new Exception(String.format("Cannot register. ID %s is already registered.", id));
    }

    /** 
     * @param id
     * @return String
     */
    private String registerEmployee(String id) {
        return String.format("Employee %s was registered successfully.", id);
    }
    
    /** 
     * @param id
     * @return String
     */
    private String updateEmployee(String id) {
        return String.format("Employee %s was updated successfully", id);
    }
    
    /** 
     * @param id
     * @param newType
     * @return String
     */
    private String promoteEmployee(String id, String newType) {
        return String.format("%s promoted successfully to %s.", id, newType);       
    }
}
