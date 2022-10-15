``` mermaid
classDiagram
    Employee <|-- Intern
    Employee <|-- Manager
    Manager  <|-- Director

    Company *-- Employee
    EmployeeFactory <-- Company

    Utils <-- Company
    Utils <-- Director
    Utils <-- Employee
    Utils <-- Manager
    Utils <-- ExceptionThrower
    
    StringCannotBeEmptyException <-- ExceptionThrower
    NegativeValueException       <-- ExceptionThrower

    EmployeeFactory <-- Employee
    EmployeeFactory <-- Manager
    EmployeeFactory <-- Director
    EmployeeFactory <-- Intern

    class Employee {
        -final String ID
        -String name
        -double grossSalary
        +Employee(String ID, String name, double grossSalary)
        +compareTo(Employee otherEmployee) int
        +getRawSalary() double
        +getNetSalary() double
        +getID() String
        +getName() String
        +getGrossSalary() double
        +setName(String newName) void
        +setGrossSalary(double newGrossSalary)
    }
    
    class Intern {
        -final double BONUS
        -int gpa
        +Intern(String ID, String name, double grossSalary, int gpa)
        +setGpa(int newGPA) void
        +getGrossSalary() double
        +getNetSalary() double
        +static checkValidGPA(int newGPA) void
    }

    class Manager {
        -String Degree
        +Manager(String ID, String name, double grossSalary, String degree)
        +updateDegree(String newDegree) void
        +getDegree() String
        +getGrossSalary() double
    }

    class Director {
        -String Department
        +Director(String ID, String name, double grossSalary, String degree, String department)
        +updateDepartment(String newDept) void
        +getDegree() String
        +getGrossSalary() double
        +getNetSalary() double
    }

    class Company {
        -ArrayList<Employee> employees
        +Company()
        +createEmployee(String employeeID, String employeeName, double grossSalary) String
        +createEmployee(String employeeID, String employeeName, double grossSalary, String degree) String
        +createEmployee(String employeeID, String employeeName, double grossSalary, String degree, String department) String
        +createEmployee(String employeeID, String employeeName, double grossSalary, int gpa) String
        +removeEmployee(String id) String
        +printEmployee(String id) String
        +printAllEmployees() String
        +printSortedEmployees() String
        +getNetSalary(String id) double
        +updateEmployeeName(String id, String newName) String
        +updateInternGPA(String id, int newGpa) String
        +updateManagerDegree(String id, String newDegree) String
        +updateDirectorDept(String id, String newDept) String
        +updateGrossSalary(String id, double newGrossSalary) String
        +getTotalNetSalary() double
        +mapEachDegree() : (HashMap~String, Integer~)
        +formatDegrees(HashMap~String, Integer~ degrees) String
        +promoteToManager(String id, String degree) String
        +promoteToDirector(String id, String degree, String department) String
        +promoteToIntern(String id, int gpa) String
        -findEmployeeIndex(String id) int
    }

    class Utils {
        +final static HashSet~String~ DEPARTMENTS
        +final static HashMap~String, Double~ DEGREES
        +final static String EOL
        +truncateDouble(double value, int decimalPlaces)$ double
        +isStringEmpty(String string)$ boolean
        +registeredEmployee(String id)$ String
        +updatedEmployee(String id)$ String
        +promotedEmployee(String id, String newType)$ String
    }

    class ExceptionThrower {
        +checkEmptyId(String id)$ void
        +checkEmptyName(String name)$ void
        +checkEmptyGrossSalary(double salary)$ void
        +checkValidDegree(String degree)$ void
        +checkIfEmployeeFound(String id, int index)$ void
        +checkIfEmployeeRegistered(String id, int index)$ void
        +checkIfNoEmployees(int size)$ void
        +checkValidDepartment(String degree)$ void
        +checkValidGPA(int newGPA)$ void
    }

    class EmployeeFactory {
        +createEmployee(String employeeID, String employeeName, double grossSalary)$ Employee
        +createEmployee(String ID, String name, double grossSalary, String degree)$ Manager
        +createEmployee(String ID, String name, double grossSalary, String degree, String department)$ Director
        +createEmployee(String employeeID, String employeeName, double grossSalary, int gpa)$ Intern
    }

    class StringCannotBeEmptyException {
        +StringCannotBeEmptyException()
        +StringCannotBeEmptyException(String message)
    }

    class NegativeValueException {
        +NegativeValueException()
        +NegativeValueException(String message)
    }
```