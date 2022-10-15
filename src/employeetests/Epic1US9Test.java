package employeetests;

import assignment3.Company;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class Epic1US9Test {


    private static final String EOL = System.lineSeparator();
    private Company facade;

    @BeforeAll
    public static void setupSystem(){
        Locale.setDefault(Locale.US);
    }

    @BeforeEach
    public void setupFacade(){
        facade = new Company();

        try{
            // 2 employees, 2 directors, 1 manager, 3 interns
            // G: Gross salary; N: Net salary
            facade.createEmployee("Emp1", "Elektra", 35000.50, "MSc", "Business");       // G: 47000.60; N: 37600.48
            facade.createEmployee("Emp2", "Blanca", 45000.00, "PhD", "Human Resources"); // G: 65750.00; N: 45450.00
            facade.createEmployee("Emp3", "Pray Tell", 25000.25, "BSc");                 // G: 27500.27; N: 24750.24
            facade.createEmployee("Emp4", "Lulu", 20000.00, 9);                          // G: 21000.00; N: 21000.00
            facade.createEmployee("Emp5", "Angel", 28500.10, 7);                         // G: 28500.10; N: 28500.10
            facade.createEmployee("Emp6", "Candy", 35000.50, 4);                         // G:     0.00; N:     0.00
            facade.createEmployee("Emp7", "Ricky", 23500.00);                            // G: 23500.00; N: 21150.00
            facade.createEmployee("Emp8", "Damon", 22100.00);                            // G: 22100.00; N: 19890.00
        }catch(Exception e){
            assertFalse(true); // Forces an error in the test. The creation should work without problems.
        }
    }

    @Test
    public void shouldUpdateEmployee() throws Exception {
        assertEquals("Employee Emp6 was updated successfully", facade.updateEmployeeName("Emp6", "Candy Ferocity"));
        assertEquals("Employee Emp6 was updated successfully", facade.updateInternGPA("Emp6", 10));
        assertEquals("Employee Emp4 was updated successfully", facade.updateInternGPA("Emp4", 2));
        assertEquals("Employee Emp1 was updated successfully", facade.updateManagerDegree("Emp1", "PhD"));
        assertEquals("Employee Emp1 was updated successfully", facade.updateDirectorDept("Emp1", "Technical"));
        assertEquals("Employee Emp8 was updated successfully", facade.updateGrossSalary("Emp8", 15000.00));
        assertEquals("Employee Emp3 was updated successfully", facade.updateGrossSalary("Emp3", 28000.99));
        assertEquals("Employee Emp3 was updated successfully", facade.updateManagerDegree("Emp3", "MSc"));

        String expectedElektra = "PhD Elektra's gross salary is 52250.67 SEK per month. Dept: Technical";
        String expectedDamon = "Damon's gross salary is 15000.00 SEK per month.";
        String expectedCandy = "Candy Ferocity's gross salary is 36000.50 SEK per month. GPA: 10";
        String expectedLulu = "Lulu's gross salary is 0.00 SEK per month. GPA: 2";
        String expectedPray = "MSc Pray Tell's gross salary is 33601.18 SEK per month.";

        assertEquals(expectedElektra,facade.printEmployee("Emp1"));
        assertEquals(expectedPray   ,facade.printEmployee("Emp3"));
        assertEquals(expectedLulu   ,facade.printEmployee("Emp4"));
        assertEquals(expectedCandy  ,facade.printEmployee("Emp6"));
        assertEquals(expectedDamon  ,facade.printEmployee("Emp8"));
    }

}
