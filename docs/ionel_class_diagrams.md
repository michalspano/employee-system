``` mermaid
classDiagram
    Employee <|-- Intern
    Employee <|-- Manager
    Manager <|-- Director

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
        - final double BONUS
        - int gpa
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
        - ArrayList<Employee> employees
        + Company()
        + createEmployee(String employeeID, String employeeName, double grossSalary) String
        + createEmployee(String employeeID, String employeeName, double grossSalary, String degree) String
        + createEmployee(String employeeID, String employeeName, double grossSalary, String degree, String department) String
        + createEmployee(String employeeID, String employeeName, double grossSalary, int gpa) String
        + removeEmployee(String id) String
        + printEmployee(String id) String
        + printAllEmployees() String
        + printSortedEmployees() String
        + getNetSalary(String id) double
        + updateEmployeeName(String id, String newName) String
        + updateInternGPA(String id, int newGpa) String
        + updateManagerDegree(String id, String newDegree) String
        + updateDirectorDept(String id, String newDept) String
        + updateGrossSalary(String id, double newGrossSalary) String
        + getTotalNetSalary() double
        + mapEachDegree() HashMap<String, Integer>
        + formatDegrees(HashMap<String, Integer> degrees) String
        + promoteToManager(String id, String degree) String
        + promoteToDirector(String id, String degree, String department) String
        + promoteToIntern(String id, int gpa) String
        - findEmployeeIndex(String id) int
    }
```