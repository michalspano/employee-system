/***************************************************************************************************
 * Group Work Assignment 3 - A3-Group 20
 * File: Company.java
 * Members: Ionel Pop, Joel Mattsson, Michal Spano
 * For DIT043: Object Oriented Programming; SEM@GU.
 ***************************************************************************************************/

package assignment3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Company 
{    
    /* Collection of type ArrayList to store the current employees of the company */
    private ArrayList<Employee> employees;

    public Company() // initial constructor
    {
        this.employees = new ArrayList<>();
    }

    // as the tests indicate, there need to be overloaded methods of the name createEmployee(...)
    // the logic of the factory is just to instantiate the objects elsewhere
    // to avoid repetition, javadoc included inside EmployeeFactory.java only

    /* Below, we list the different overloaded methods to create different types of Employees per the specifications */
    
    public String createEmployee(String employeeID, String employeeName, double grossSalary) throws Exception 
    {
        /* Include in the analysis of design
         * Method to ensure that no repeated Employees are registered (applies for all overloaded methods)
         * We call the method as the first procedure in the method, due to the following reasons:
         * 1. if the employee is already registered, we don't need to carry any of the other procedures within the method
         * 2. the method depends on the `findEmployeeIndex()` method, which is a private method of this class and thus, 
         * it cannot be called from outside the class. We could move it to the Utils class, etc., but it is not necessary, 
         * as it is only used in this class and it requires the `employees` collection.
         * So, we don't need to pass the reference to the collection as a parameter, as it is already a member of the class. */

        ExceptionThrower.checkIfEmployeeRegistered(employeeID, findEmployeeIndex(employeeID));

        // create the object with the factory method
        Employee newEmployee = EmployeeFactory.createEmployee(employeeID, employeeName, grossSalary);
        this.employees.add(newEmployee);

        return Utils.registeredEmployee(employeeID);
    }
    
    public String createEmployee(String employeeID, String employeeName, double grossSalary, String degree) throws Exception 
    {
        ExceptionThrower.checkIfEmployeeRegistered(employeeID, findEmployeeIndex(employeeID));

        // create the object with the factory method
        Employee newEmployee = EmployeeFactory.createManager(employeeID, employeeName, grossSalary, degree);
        this.employees.add(newEmployee);

        return Utils.registeredEmployee(employeeID);
    }
    
    public String createEmployee(String employeeID, String employeeName, double grossSalary, String degree, String department) throws Exception 
    {
        ExceptionThrower.checkIfEmployeeRegistered(employeeID, findEmployeeIndex(employeeID));

        Employee newEmployee = EmployeeFactory.createDirector(employeeID, employeeName, grossSalary, degree, department);
        this.employees.add(newEmployee);

        return Utils.registeredEmployee(employeeID);
    }

    public String createEmployee(String employeeID, String employeeName, double grossSalary, int gpa) throws Exception 
    {
        ExceptionThrower.checkIfEmployeeRegistered(employeeID, findEmployeeIndex(employeeID));

        Employee newEmployee = EmployeeFactory.createIntern(employeeID, employeeName, grossSalary, gpa);
        this.employees.add(newEmployee);

        return Utils.registeredEmployee(employeeID);
    }
    
    /** 
     * @param id
     * @throws Exception
     */
    public String removeEmployee(String id) throws Exception
    {
        int employeeIndex = findEmployeeIndex(id);

        ExceptionThrower.checkIfEmployeeFound(id, employeeIndex);
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

        ExceptionThrower.checkIfEmployeeFound(id, employeeIndex);
        Employee currentEmployee = this.employees.get(employeeIndex);

        return currentEmployee.toString();
    }
    
    /** 
     * @return String
     */
    public String printAllEmployees() throws Exception
    {  
        // if no Employees are found; don't continue with the algorithm
        ExceptionThrower.checkIfNoEmployees(this.employees.size());

        String msg = "All registered employees:" + Utils.EOL; // initial string header

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
        ExceptionThrower.checkIfNoEmployees(this.employees.size()); // this needs to be checked before accessing the body of this method

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
    }

    /** 
     * @param id
     * @return double
     */
    public double getNetSalary(String id) throws Exception 
    {
        int employeeIndex = findEmployeeIndex(id);

        ExceptionThrower.checkIfEmployeeFound(id, employeeIndex);
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
        int employeeIndex = findEmployeeIndex(id);
        ExceptionThrower.checkIfEmployeeFound(id, employeeIndex);

        // set the new name 
        this.employees.get(employeeIndex).setName(newName);

        return Utils.updatedEmployee(id);
    }
    
    /** 
     * @param id
     * @param newGpa
     * @return String
     */
    public String updateInternGPA(String id, int newGpa) throws Exception
    {
        int employeeIndex = findEmployeeIndex(id);

        ExceptionThrower.checkIfEmployeeFound(id, employeeIndex);
        Employee currentEmployee = this.employees.get(employeeIndex);

        // ensure that type is Intern
        if (!(currentEmployee instanceof Intern)) return "";

        // perform down-casting to access the 'setGpa()' method
        Intern internEmployee = (Intern) currentEmployee;
        internEmployee.setGpa(newGpa);

        return Utils.updatedEmployee(id);
    }
    
    /** 
     * @param id
     * @param newDegree
     * @return String
     */
    public String updateManagerDegree(String id, String newDegree) throws Exception
    {
        int employeeIndex = findEmployeeIndex(id);
        ExceptionThrower.checkIfEmployeeFound(id, employeeIndex);

        Employee currentEmployee = this.employees.get(employeeIndex);

        // ensure that type is Manager
        if (!(currentEmployee instanceof Manager)) return "";

        // perform down-casting to access the `updateDegree()` method
        Manager managerEmployee = (Manager) currentEmployee;
        managerEmployee.updateDegree(newDegree);

        return Utils.updatedEmployee(id);
    }
    
    /** 
     * @param id
     * @param newDept
     * @return String
     */
    public String updateDirectorDept(String id, String newDept) throws Exception
    {
        int employeeIndex = findEmployeeIndex(id);

        ExceptionThrower.checkIfEmployeeFound(id, employeeIndex);
        Employee currentEmployee = this.employees.get(employeeIndex);

        // ensure that type is Director
        if (!(currentEmployee instanceof Director)) return "";

        // perform down-casting to access the 'updateDepartment()' method
        Director directorEmployee = (Director) currentEmployee;
        directorEmployee.updateDepartment(newDept);

        return Utils.updatedEmployee(id);
    }

    /** 
     * @param id
     * @param newGrossSalary
     * @return String
     */
    public String updateGrossSalary(String id, double newGrossSalary) throws Exception
    {           
        int employeeIndex = findEmployeeIndex(id);
        ExceptionThrower.checkIfEmployeeFound(id, employeeIndex);

        // update the salary
        this.employees.get(employeeIndex).setGrossSalary(newGrossSalary);

        return Utils.updatedEmployee(id);
    }

    /**
     * @return double
     */
    public double getTotalNetSalary() throws Exception
    {
        // don't perform the following if no Employees found
        ExceptionThrower.checkIfNoEmployees(this.employees.size());

        double sum = 0.0;

        for (Employee employee : this.employees) {
            sum += employee.getNetSalary();
        }

        return sum;
    }
    
    /** 
     * @return HashMap<String, Integer>
     */

    /*
     * TODO: this method should return String in the specifications
     * If there are no employees registered with a specific degree, the
     * corresponding row is simply not printed.
     * However, in the tests, the expected type is a HashMap, not a String
     * Make a method for formatting too
     */

    public HashMap<String, Integer> mapEachDegree() throws Exception
    {
        // don't perform the algorithm if no Employees are found
        ExceptionThrower.checkIfNoEmployees(this.employees.size());

        HashMap<String, Integer> degreesMap = new HashMap<String, Integer>();

        for (Employee currentEmployee : this.employees)
        {
            if (currentEmployee instanceof Manager)
            {  
                // perform down-casting to obtain the degree
                Manager newManager = (Manager) currentEmployee;
                String currentDegree = newManager.getDegree();

                // if the key is not present, add it and make value of it 0
                if (!degreesMap.containsKey(currentDegree)) {
                    degreesMap.put(currentDegree, 0);
                }

                // general case: access the current value and increment it
                int currentDegreeCounter = degreesMap.get(currentDegree);
                degreesMap.put(currentDegree, currentDegreeCounter + 1);
            }
        }

        return degreesMap;
    }
    
    /** Currently not used, the tests don't expect this functionality (even though the instruction do)
     * @param degrees
     * @return String
     */
    public String formatDegrees(HashMap<String, Integer> degrees) {
        String result = "Academic background of employees:" + Utils.EOL;

        for (String key : degrees.keySet()) {

            int degreeCount = degrees.get(key);
            if (degreeCount > 0) { // only rows with more than 0 degrees are printed
                result += String.format("%s: => %d", key, degreeCount) + Utils.EOL;
            }
        }

        return result;
    }

    /* ArrayList.set();
     * Docs: https://docs.oracle.com/javase/7/docs/api/java/util/ArrayList.html
     * 
     *              set(int index, E element)
     * Replaces the element at the specified position in this list with the
     * specified element.
     * 
     * Last accessed: 10.10.2022 */

    /**
     * @param id
     * @param degree
     * @return String
     * @throws Exception
     */
    public String promoteToManager(String id, String degree) throws Exception
    {   
        // ensure that the Employee given byt the ID exits
        int employeeIndex = findEmployeeIndex(id);
        ExceptionThrower.checkIfEmployeeFound(id, employeeIndex);

        Employee currentEmployee = this.employees.get(employeeIndex);

        // copy the required data from the Employee to a Manager
        Manager newManager = EmployeeFactory.createManager(currentEmployee.getID(), 
                                                           currentEmployee.getName(), 
                                                           currentEmployee.getRawSalary(), 
                                                           degree);

        // replace the Employee with type Manager at the index
        this.employees.set(employeeIndex, newManager);

        return Utils.promotedEmployee(id, "Manager");
    }
    
    /** 
     * @param id
     * @param degree
     * @param department
     * @return String
     */
    public String promoteToDirector(String id, String degree, String department) throws Exception
    {
        // ensure that the Employee given byt the ID exits
        int employeeIndex = findEmployeeIndex(id);
        ExceptionThrower.checkIfEmployeeFound(id, employeeIndex);

        Employee currentEmployee = this.employees.get(employeeIndex);

        // copy the required data from the Employee to a Director (sub-type Manage)
        Director newDirector = EmployeeFactory.createDirector(currentEmployee.getID(), 
                                            currentEmployee.getName(), 
                                            currentEmployee.getRawSalary(), 
                                            degree, department);
        
        // replace the Employee with type Director at the index
        this.employees.set(employeeIndex, newDirector);
        return Utils.promotedEmployee(id, "Director");
    }
    
    /** 
     * @param id
     * @param gpa
     * @return String
     */
    public String promoteToIntern(String id, int gpa) throws Exception
    {
        // ensure that the Employee given byt the ID exits
        int employeeIndex = findEmployeeIndex(id);
        ExceptionThrower.checkIfEmployeeFound(id, employeeIndex);

        Employee currentEmployee = this.employees.get(employeeIndex);

        Intern newIntern = EmployeeFactory.createIntern(currentEmployee.getID(), 
                                                        currentEmployee.getName(), 
                                                        currentEmployee.getRawSalary(), 
                                                        gpa);
        
        // replace the Employee with type Intern at the index
        this.employees.set(employeeIndex, newIntern);
        return Utils.promotedEmployee(id, "Intern");
    }
    
    // find the index of the Employee within the company; if not, return -1
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