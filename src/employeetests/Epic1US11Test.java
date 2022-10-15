package employeetests;

import assignment3.Company;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class Epic1US11Test {
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
    public void shouldPromoteToManager() throws Exception {
        String damonID = "Emp8";
        String expectedMessage = "Emp8 promoted successfully to Manager.";
        String expectedEmployee = "PhD Damon's gross salary is 29835.00 SEK per month.";
        assertEquals(expectedMessage , facade.promoteToManager(damonID, "PhD"));
        assertEquals(expectedEmployee, facade.printEmployee(damonID));

        String elektraID = "Emp1";
        // Note, when converting, it will get Elektra's raw gross salary, i.e.,
        // the gross salary before the bonuses: 35000.50
        expectedMessage = "Emp1 promoted successfully to Manager.";
        expectedEmployee = "BSc Elektra's gross salary is 38500.55 SEK per month.";
        assertEquals(expectedMessage , facade.promoteToManager(elektraID, "BSc"));
        assertEquals(expectedEmployee, facade.printEmployee(elektraID));

        String angelID = "Emp5";
        expectedMessage = "Emp5 promoted successfully to Manager.";
        expectedEmployee = "MSc Angel's gross salary is 34200.11 SEK per month.";
        assertEquals(expectedMessage , facade.promoteToManager(angelID, "MSc"));
        assertEquals(expectedEmployee, facade.printEmployee(angelID));
    }

    @Test
    public void promoteToDirector() throws Exception {
        String rickyID = "Emp7";
        String expectedMessage = "Emp7 promoted successfully to Director.";
        String expectedEmployee = "PhD Ricky's gross salary is 36725.00 SEK per month. Dept: Human Resources";
        assertEquals(expectedMessage , facade.promoteToDirector(rickyID, "PhD", "Human Resources"));
        assertEquals(expectedEmployee, facade.printEmployee(rickyID));

        String candyID = "Emp6";
        expectedMessage = "Emp6 promoted successfully to Director.";
        expectedEmployee = "BSc Candy's gross salary is 43500.55 SEK per month. Dept: Technical";
        assertEquals(expectedMessage , facade.promoteToDirector(candyID, "BSc", "Technical"));
        assertEquals(expectedEmployee, facade.printEmployee(candyID));

        String prayID = "Emp3";
        expectedMessage = "Emp3 promoted successfully to Director.";
        expectedEmployee = "BSc Pray Tell's gross salary is 32500.27 SEK per month. Dept: Business";
        assertEquals(expectedMessage , facade.promoteToDirector(prayID, "BSc", "Business"));
        assertEquals(expectedEmployee, facade.printEmployee(prayID));
    }

    @Test
    public void promoteToIntern() throws Exception {
        String blancaID = "Emp2";
        String expectedMessage = "Emp2 promoted successfully to Intern.";
        String expectedEmployee = "Blanca's gross salary is 46000.00 SEK per month. GPA: 10";
        assertEquals(expectedMessage , facade.promoteToIntern(blancaID, 10));
        assertEquals(expectedEmployee, facade.printEmployee(blancaID));

        String damonID = "Emp8";
        expectedMessage = "Emp8 promoted successfully to Intern.";
        expectedEmployee = "Damon's gross salary is 0.00 SEK per month. GPA: 1";
        assertEquals(expectedMessage , facade.promoteToIntern(damonID, 1));
        assertEquals(expectedEmployee, facade.printEmployee(damonID));

        String prayID = "Emp3";
        expectedMessage = "Emp3 promoted successfully to Intern.";
        expectedEmployee = "Pray Tell's gross salary is 25000.25 SEK per month. GPA: 6";
        assertEquals(expectedMessage , facade.promoteToIntern(prayID, 6));
        assertEquals(expectedEmployee, facade.printEmployee(prayID));
    }
}
