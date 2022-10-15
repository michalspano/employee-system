/***************************************************************************************************
 * Group Work Assignment 3 - A3-Group 20
 * File: Company.java
 * Members: Ionel Pop, Joel Mattsson, Michal Spano
 * For DIT043: Object Oriented Programming; SEM@GU.
 ***************************************************************************************************/

package assignment3;

import java.util.HashMap;       // used to store individual employee's degrees
import java.util.ArrayList;     // used to store the employees
import java.util.Collections;   // used to sort the employees via Comparable interface

public class Company 
{    
    private ArrayList<Employee> employees;

    /* Future idea: to store the employees, we could use a LinkedHashMap, such that each key represents an employee,
     * that is, the ID of the employee, and the value is the object with the properties of the employee. Therefore,
     * we could enhance the search for an employee by ID to O(1) instead of O(n).
     * 
     * Docs: https://docs.oracle.com/javase/8/docs/api/java/util/LinkedHashMap.html
     * Last accessed: 15.10. 2022 */

    public Company() 
    {
        this.employees = new ArrayList<>();
    }

    /* Below, we list the different overloaded methods to create different types of Employees per the specifications 
     * of the assignment. The actual creation via the `new` keyword takes place in the EmployeeFactory class. */
    
    public String createEmployee(String employeeID, String employeeName, double grossSalary) throws Exception 
    {
        // ensure that the employee does not already exist
        ExceptionThrower.checkIfEmployeeRegistered(employeeID, findEmployeeIndex(employeeID));

        // create the object with the factory method
        Employee newEmployee = EmployeeFactory.createEmployee(employeeID, employeeName, grossSalary);
        this.employees.add(newEmployee);

        return Utils.registeredEmployee(employeeID);
    }
    
    public String createEmployee(String employeeID, String employeeName, double grossSalary, String degree) throws Exception 
    {
        ExceptionThrower.checkIfEmployeeRegistered(employeeID, findEmployeeIndex(employeeID));

        // create manager object
        Employee newManager = EmployeeFactory.createManager(employeeID, employeeName, grossSalary, degree);
        this.employees.add(newManager);

        return Utils.registeredEmployee(employeeID);
    }
    
    public String createEmployee(String employeeID, String employeeName, double grossSalary, String degree, String department) throws Exception 
    {
        ExceptionThrower.checkIfEmployeeRegistered(employeeID, findEmployeeIndex(employeeID));

        // create director object
        Employee newDirector = EmployeeFactory.createDirector(employeeID, employeeName, grossSalary, degree, department);
        this.employees.add(newDirector);

        return Utils.registeredEmployee(employeeID);
    }

    public String createEmployee(String employeeID, String employeeName, double grossSalary, int gpa) throws Exception 
    {
        ExceptionThrower.checkIfEmployeeRegistered(employeeID, findEmployeeIndex(employeeID));

        // create intern object
        Employee newIntern = EmployeeFactory.createIntern(employeeID, employeeName, grossSalary, gpa);
        this.employees.add(newIntern);

        return Utils.registeredEmployee(employeeID);
    }
    
    /** 
     * @param id
     * @throws Exception
     */
    public String removeEmployee(String id) throws Exception
    {
        int employeeIndex = findEmployeeIndex(id);

        // ensure that the index is valid; if not, throw an exception
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

        // ensure that the index is valid; if not, throw an exception
        ExceptionThrower.checkIfEmployeeFound(id, employeeIndex);

        Employee currentEmployee = this.employees.get(employeeIndex);

        return currentEmployee.toString();
    }
    
    /** 
     * @return String
     */
    public String printAllEmployees() throws Exception
    {  
        // if no Employees are found; don't continue with the algorithm - throw an exception
        int numberOfEmployees = this.employees.size();
        ExceptionThrower.checkIfNoEmployees(numberOfEmployees);

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
        // ensure that there are employees to sort; otherwise, throw an exception
        ExceptionThrower.checkIfNoEmployees(this.employees.size()); 

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
            addAll(employees); // copy the employees
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

        // ensure that the index is valid; if not, throw an exception
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

        // ensure that the index is valid; if not, throw an exception
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

        // ensure that the index is valid; if not, throw an exception
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

        // ensure that the index is valid; if not, throw an exception
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

        // ensure that the index is valid; if not, throw an exception
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

        // ensure that the index is valid; if not, throw an exception
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
        // ensure that there are employees; otherwise, throw an exception
        ExceptionThrower.checkIfNoEmployees(this.employees.size());

        double sum = 0.0;

        for (Employee employee : this.employees) {
            sum += employee.getNetSalary();
        }

        return sum;
    }
    
    
    /* Analysis mapEachDegree() method:
    * This method should return String in the specifications.
    * If there are no employees registered with a specific degree, the corresponding row is simply not printed.
    * However, in the tests, the expected type is a HashMap, not a String. Below, is a method for formatting
    * the string. It would need to be called inside the mapEachDegree() method. */
    
    /** 
     * @return HashMap<String, Integer>
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
    
    /* currently unused; explanation above^
    public String formatDegrees(HashMap<String, Integer> degrees) {
    
        String result = "Academic background of employees:" + Utils.EOL;
        
        for (String key : degrees.keySet()) {
            
            int degreeCount = degrees.get(key);
            if (degreeCount > 0) { // only rows with more than 0 degrees are printed
            result += String.format("%s: => %d", key, degreeCount) + Utils.EOL;
            }
        }
    
        return result;
    } */
    
    /**
     * @param id
     * @param degree
     * @return String
     * @throws Exception
     */
    public String promoteToManager(String id, String degree) throws Exception
    {   
        int employeeIndex = findEmployeeIndex(id);

        // ensure that the Employee given byt the ID exits
        ExceptionThrower.checkIfEmployeeFound(id, employeeIndex);

        Employee currentEmployee = this.employees.get(employeeIndex);

        // copy the required data from the Employee to a Manager
        Manager newManager = EmployeeFactory.createManager(currentEmployee.getID(), 
                                                           currentEmployee.getName(), 
                                                           currentEmployee.getRawSalary(), 
                                                           degree);

        /* ArrayList.set();
         * Docs: https://docs.oracle.com/javase/7/docs/api/java/util/ArrayList.html
         * 
         *      set(int index, E element)
         * Replaces the element at the specified position in this list with the
         * specified element.
         * Last accessed: 10.10.2022 */

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
        int employeeIndex = findEmployeeIndex(id);

        // ensure that the Employee given byt the ID exits
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
        int employeeIndex = findEmployeeIndex(id);

        // ensure that the Employee given byt the ID exits
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
    
    /* find the index of the Employee within the company; if not, return -1
     * this is a private method specific to the Company class */

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