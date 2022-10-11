package assignment3;

public enum Departments {

    Business("Business"),
    HumanResources("Human Resources"),
    Technical("Technical");

    public final String match;

    Departments(String match)
    {
        this.match = match;
    }
}
