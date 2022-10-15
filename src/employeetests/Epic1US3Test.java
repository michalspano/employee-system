package employeetests;

import assignment3.Company;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class Epic1US3Test {

    private static final String EOL = System.lineSeparator();

    @BeforeAll
    public static void setupSystem(){
        Locale.setDefault(Locale.US);
    }

    @Test
    public void shouldCreateManagerEmployee() throws Exception {
        Company facade = new Company();
        String employeeID = "IDb";
        String employeeName = "Mary Keller";
        String degree = "PhD";
        double grossSalary = 62000.009; // Should be truncated when created. See specification.

        String expectedMessage = "Employee IDb was registered successfully.";
        String actualMessage = facade.createEmployee(employeeID, employeeName, grossSalary, degree);
        assertEquals(expectedMessage, actualMessage);

        String expectedPrint = "PhD Mary Keller's gross salary is 83700.00 SEK per month.";
        String actualPrint = facade.printEmployee(employeeID);
        assertEquals(expectedPrint, actualPrint);

        // 62000.00 * 1.35 (PhD Bonus) = 83700.00 - (0.1 * 83700.00) =  75330.0;
        double netSalary = facade.getNetSalary(employeeID);
        assertEquals(75330.00, netSalary);
    }

    @Test
    public void shouldCreateDirectorEmployee() throws Exception {
        Company facade = new Company();
        String employeeID = "IDc";
        String employeeName = "Alan Turing";
        String degree = "PhD";
        String dept = "Technical";
        double grossSalary = 62000.00; // Should be truncated

        String expectedMessage = "Employee IDc was registered successfully.";
        String actualMessage = facade.createEmployee(employeeID, employeeName, grossSalary, degree, dept);
        assertEquals(expectedMessage, actualMessage);

        String expectedPrint = "PhD Alan Turing's gross salary is 88700.00 SEK per month. Dept: Technical";
        String actualPrint = facade.printEmployee(employeeID);
        assertEquals(expectedPrint, actualPrint);
        assertEquals(59220.00, facade.getNetSalary(employeeID));

        // ---- A second director just for testing.
        String angelicaID = "IDd";
        String angelicaName = "Angelica Ross";
        String angelicaDept = "Business";
        String angelicaDegree = "BSc";
        double angelicaSalary = 20000.00;

        expectedMessage = "Employee IDd was registered successfully.";
        actualMessage = facade.createEmployee(angelicaID, angelicaName, angelicaSalary, angelicaDegree, angelicaDept);
        assertEquals(expectedMessage, actualMessage);

        expectedPrint = "BSc Angelica Ross's gross salary is 27000.00 SEK per month. Dept: Business";
        actualPrint = facade.printEmployee(angelicaID);
        assertEquals(expectedPrint, actualPrint);
        assertEquals(24300.00, facade.getNetSalary(angelicaID));

        // ---- A third director just for testing.
        String graceID = "IDe";
        String graceName = "Grace Hopper";
        String graceDept = "Human Resources";
        String graceDegree = "MSc";
        double graceSalary = 27000.00;

        expectedMessage = "Employee IDe was registered successfully.";
        actualMessage = facade.createEmployee(graceID, graceName, graceSalary, graceDegree, graceDept);
        assertEquals(expectedMessage, actualMessage);

        expectedPrint = "MSc Grace Hopper's gross salary is 37400.00 SEK per month. Dept: Human Resources";
        actualPrint = facade.printEmployee(graceID);
        assertEquals(expectedPrint, actualPrint);
        assertEquals(29920.00, facade.getNetSalary(graceID));
    }

    @Test
    public void shouldCreateInternEmployee() throws Exception {
        Company facade = new Company();
        String angelaID = "IDf";
        String angelaName = "Angela Martin";
        double angelaSalary = 15000.00;
        int angelaGPA = 9;

        String expectedMessage = "Employee IDf was registered successfully.";
        String actualMessage = facade.createEmployee(angelaID, angelaName, angelaSalary, angelaGPA);
        assertEquals(expectedMessage, actualMessage);

        String expectedPrint = "Angela Martin's gross salary is 16000.00 SEK per month. GPA: 9";
        String actualPrint = facade.printEmployee(angelaID);
        assertEquals(expectedPrint, actualPrint);
        assertEquals(16000.00, facade.getNetSalary(angelaID));

        // ---- second intern with average grade
        String dwitghtID = "IDg";
        String dwitghtName = "Dwight Schrute";
        double dwitghtSalary = 15000.00;
        int dwightGPA = 7;

        expectedMessage = "Employee IDg was registered successfully.";
        actualMessage = facade.createEmployee(dwitghtID, dwitghtName, dwitghtSalary, dwightGPA);
        assertEquals(expectedMessage, actualMessage);

        expectedPrint = "Dwight Schrute's gross salary is 15000.00 SEK per month. GPA: 7";
        actualPrint = facade.printEmployee(dwitghtID);
        assertEquals(expectedPrint, actualPrint);
        assertEquals(15000.00, facade.getNetSalary(dwitghtID));

        // ---- third intern with average grade
        String michaelID = "IDh";
        String michaelName = "Michael Scott";
        double michaelSalary = 15000.00;
        int michaelGPA = 3;

        expectedMessage = "Employee IDh was registered successfully.";
        actualMessage = facade.createEmployee(michaelID, michaelName, michaelSalary, michaelGPA);
        assertEquals(expectedMessage, actualMessage);

        expectedPrint = "Michael Scott's gross salary is 0.00 SEK per month. GPA: 3";
        actualPrint = facade.printEmployee(michaelID);
        assertEquals(expectedPrint, actualPrint);
        assertEquals(0.00, facade.getNetSalary(michaelID));
    }
}
