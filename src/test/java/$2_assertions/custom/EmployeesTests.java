package $2_assertions.custom;

import employee.Employee;
import employee.EmployeeRole;
import employee.Employees;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static employee.EmployeeRole.CEO;
import static employee.EmployeeRole.HR;
import static org.junit.Assert.assertEquals;

public class EmployeesTests {

    @Test
    public void shouldCreateEmployeeWithName() {
        Employees employees = new Employees();

        Employee newEmployee = employees.createWithName("Adam", "Nowak");

        assertHasName("Adam", "Nowak", newEmployee);
    }

    @Test
    public void shouldFindEmployeesByRole() throws Exception {
        // given
        Employees employees = new Employees(
                list(employeeOfRole(CEO), employeeOfRole(CEO), employeeOfRole(HR)));

        // when
        List<Employee> foundEmployees = employees.findByRole(CEO);

        // then
        EmployeesAssert.on(foundEmployees).containsOnlyEmployeesOfRole(CEO);
    }

    @SafeVarargs
    private final <T> List<T> list(T... elements) {
        return Arrays.stream(elements).collect(Collectors.toList());
    }

    private Employee employeeOfRole(EmployeeRole employeeRole) {
        return new Employee(1, "", "", employeeRole);
    }

    private void assertHasName(String givenName, String surname, Employee newEmployee) {
        assertEquals(givenName, newEmployee.givenName);
        assertEquals(surname, newEmployee.surname);
    }
}
