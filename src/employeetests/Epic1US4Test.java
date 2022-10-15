package employeetests;

import assignment3.Company;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Epic1US4Test {

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
    public void shouldRemoveEmployee() throws Exception {
        String empID = "Emp8";
        String expectedMessage = "Employee Emp8 was successfully removed.";
        String actualMessage = facade.removeEmployee(empID);
        assertEquals(expectedMessage, actualMessage);

        // Since it was removed you can create the Employee with the ID again.
        String expectedLeiomy = "Employee Emp8 was registered successfully.";
        String actualLeiomy = facade.createEmployee("Emp8", "Leiomy Maldonado", 32500.00);
        assertEquals(expectedLeiomy, actualLeiomy);

        String expectedPrint = "Leiomy Maldonado's gross salary is 32500.00 SEK per month.";
        String actualPrint = facade.printEmployee(empID);
        assertEquals(expectedPrint, actualPrint);
    }
}
