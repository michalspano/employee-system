package employeetests;

import assignment3.Company;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class Epic1US1Test {


    private static final String EOL = System.lineSeparator();
    private Company facade;

    @BeforeAll
    public static void setupSystem(){
        Locale.setDefault(Locale.US);
    }

    @Test
    public void shouldCreateRegularEmployee() throws Exception {
        Company facade = new Company();
        String employeeID = "IDa";
        String employeeName = "Katherine Johnson";
        double grossSalary = 50000.00;

        String expectedMessage = "Employee IDa was registered successfully.";
        String actualMessage = facade.createEmployee(employeeID, employeeName, grossSalary);
        assertEquals(expectedMessage, actualMessage);

        String expectedPrint = "Katherine Johnson's gross salary is 50000.00 SEK per month.";
        String actualPrint = facade.printEmployee(employeeID);
        assertEquals(expectedPrint, actualPrint);

        // 50000.00 - (0.1 * 50000.00) =  45000.0;
        double netSalary = facade.getNetSalary(employeeID);
        assertEquals(45000.00, netSalary);
    }

    @Test
    public void shouldTruncateSalary() throws Exception {
        Company facade = new Company();
        String employeeID = "IDa";
        String employeeName = "Hedy Lamar";
        double grossSalary = 20123.12845;

        String expectedMessage = "Employee IDa was registered successfully.";
        String actualMessage = facade.createEmployee(employeeID, employeeName, grossSalary);
        assertEquals(expectedMessage, actualMessage);

        String expectedPrint = "Hedy Lamar's gross salary is 20123.12 SEK per month.";
        String actualPrint = facade.printEmployee(employeeID);
        assertEquals(expectedPrint, actualPrint);

        // 20123.12 - (0.1 * 20123.12) =  20123.12 - 2012.312 = 18110.80 (truncated after calculation);
        double netSalary = facade.getNetSalary(employeeID);
        assertEquals(18110.80, netSalary);
    }
}
