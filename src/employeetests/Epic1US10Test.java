package employeetests;

import assignment3.Company;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Locale;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class Epic1US10Test {

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
    public void shouldMapPerDegree() throws Exception {
        Map<String, Integer> actualMap = facade.mapEachDegree();
        assertEquals(actualMap.get("PhD"), 1);
        assertEquals(actualMap.get("MSc"), 1);
        assertEquals(actualMap.get("BSc"), 1);

        facade.removeEmployee("Emp2"); // Remove Blanca with the PhD
        //Adds temporary employees with more 3 MSc and 1 BSc
        facade.createEmployee("Temp1", "John Doe", 25000.0, "MSc");
        facade.createEmployee("Temp2", "Jane Doe", 25000.0, "MSc");
        facade.createEmployee("Temp3", "Mary Doe", 25000.0, "MSc");
        facade.createEmployee("Temp4", "Mark Doe", 25000.0, "BSc");

        actualMap = facade.mapEachDegree();
        assertFalse(actualMap.containsKey("PhD")); // there should not be a PhD anymore.
        assertEquals(actualMap.get("MSc"), 4);
        assertEquals(actualMap.get("BSc"), 2);
    }

    @Test
    public void shouldMapPerMissingDegree() throws Exception {
        facade = new Company();
        //Adds temporary employees with more 3 MSc and 1 BSc
        facade.createEmployee("Temp1", "John Doe", 25000.0, "BSc");
        facade.createEmployee("Temp2", "Jane Doe", 25000.0, "BSc");
        facade.createEmployee("Temp3", "Mary Doe", 25000.0, "BSc");
        facade.createEmployee("Temp4", "Mark Doe", 25000.0, "BSc");

        Map<String, Integer> actualMap = facade.mapEachDegree();
        assertFalse(actualMap.containsKey("PhD")); // there should not be a PhD anymore.
        assertFalse(actualMap.containsKey("MSc")); // there should not be a PhD anymore.
        assertEquals(actualMap.get("BSc"), 4);

        facade.createEmployee("Temp5", "Owen Doe", 25000.0, "MSc");
        actualMap = facade.mapEachDegree();
        assertFalse(actualMap.containsKey("PhD")); // there should not be a PhD anymore.
        assertEquals(actualMap.get("BSc"), 4);
        assertEquals(actualMap.get("MSc"), 1);
    }
}
