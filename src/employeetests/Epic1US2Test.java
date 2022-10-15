package employeetests;

import assignment3.Company;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class Epic1US2Test {

    private static final String EOL = System.lineSeparator();
    // Test with two different companies.
    private Company netflix;
    private Company hbo;

    @BeforeAll
    public static void setupSystem(){
        Locale.setDefault(Locale.US);
    }

    @BeforeEach
    public void setupFacade(){
        netflix = new Company();

        try{
            // 2 employees, 2 directors, 1 manager, 3 interns
            // G: Gross salary; N: Net salary
            netflix.createEmployee("Emp1", "Elektra", 35000.50, "MSc", "Business");       // G: 47000.60; N: 37600.48
            netflix.createEmployee("Emp2", "Blanca", 45000.00, "PhD", "Human Resources"); // G: 65750.00; N: 45450.00
            netflix.createEmployee("Emp3", "Pray Tell", 25000.25, "BSc");                 // G: 27500.27; N: 24750.24
            netflix.createEmployee("Emp4", "Lulu", 20000.00, 9);                          // G: 21000.00; N: 21000.00
            netflix.createEmployee("Emp5", "Angel", 28500.10, 7);                         // G: 28500.10; N: 28500.10
            netflix.createEmployee("Emp6", "Candy", 35000.50, 4);                         // G:     0.00; N:     0.00
            netflix.createEmployee("Emp7", "Ricky", 23500.00);                            // G: 23500.00; N: 21150.00
            netflix.createEmployee("Emp8", "Damon", 22100.00);                            // G: 22100.00; N: 19890.00
        }catch(Exception e){
            assertFalse(true); // Forces an error in the test. The creation should work without problems.
        }
    }

    @Test
    public void shouldBeDifferentCompanies() throws Exception {
        Company netflix = new Company();
        Company hbo = new Company();

        String sameID = "IDa"; // okay since they are in different companies.

        String nancyName = "Nancy";
        double nancySalary = 23000.00;

        String nancyMessage = "Employee IDa was registered successfully.";
        String nancyResult = netflix.createEmployee(sameID, nancyName, nancySalary);
        assertEquals(nancyMessage, nancyResult);

        String juneName = "June";
        double juneSalary = 25000.00;

        String juneMessage = "Employee IDa was registered successfully.";
        String juneResult = hbo.createEmployee(sameID, juneName, juneSalary);
        assertEquals(juneMessage, juneResult);

        String junePrint = "June's gross salary is 25000.00 SEK per month.";
        String actualPrintJune = hbo.printEmployee(sameID);
        assertEquals(junePrint, actualPrintJune);

        String nancyPrint = "Nancy's gross salary is 23000.00 SEK per month.";
        String actualPrintNancy = netflix.printEmployee(sameID);
        assertEquals(nancyPrint, actualPrintNancy);
    }

}
