``` mermaid
classDiagram
    Animal <|-- Duck
    Animal <|-- Fish
    Animal <|-- Zebra

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
    
    class Intern{
        - final double BONUS
        - int gpa
        +Intern(String ID, String name, double grossSalary, int gpa)
        +setGpa(int newGPA) void
        +getGrossSalary() double
        +getNetSalary() double
        +static checkValidGPA(int newGPA) void
    }

    class Manager{
        -String Degree
        +Manager(String ID, String name, double grossSalary, String degree)
        +updateDegree(String newDegree) void
        +getDegree() String
        +getGrossSalary() double
    }

    class Director{
        -String Department
        +Director(String ID, String name, double grossSalary, String degree, String department)
        +updateDepartment(String newDept) void
        +getDegree() String
        +getGrossSalary() double
        +getNetSalary() double
    }
```