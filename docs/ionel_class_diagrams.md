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
        +setName(String newName)
        +setGrossSalary(double newGrossSalary)

    }
    
    class Duck{
        +String beakColor
        +swim()
        +quack()
    }
    class Fish{
        -int sizeInFeet
        -canEat()
    }
    class Zebra{
        +bool is_wild
        +run()
    }
```