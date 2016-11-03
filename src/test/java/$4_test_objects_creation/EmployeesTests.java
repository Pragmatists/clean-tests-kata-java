package $4_test_objects_creation;

import employee.Employee;
import employee.EmployeeRole;
import employee.Employees;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static employee.Employee.anEmployee;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class EmployeesTests {

    @Test
    public void shouldFindEmployeeById() {
        Employee employee1 = new Employee(1, "Jan", "Wokalski", EmployeeRole.CEO);
        Employee employee2 = new Employee(2, "Adam", "Nowak", EmployeeRole.HR);
        Employees employees = new Employees(list(employee1, employee2));

        Employee foundEmployee = employees.findById(2);

        assertEquals(new Employee(2, "Adam", "Nowak", EmployeeRole.HR), foundEmployee);
    }

    @Test
    public void shouldRemoveEmployeeById() {
        Employee employee1 = new Employee(1, "Jan", "Wokalski", EmployeeRole.CEO);
        Employee employee3 = new Employee(3, "Jan", "Wokalski", EmployeeRole.HR);
        Employee employee4 = new Employee(4, "Jan", "Wokalski", EmployeeRole.DEFAULT);
        Employee employee2 = new Employee(2, "Adam", "Nowak", EmployeeRole.HR);
        Employees employees = new Employees(list(employee1, employee2, employee3, employee4));

        employees.remove(2);

        assertEquals(3, employees.size());
        assertTrue(employees.contains(employee1));
        assertThat(employees).extracting("id").containsOnly(1, 4, 3);
        assertThat(employees).extracting("givenName").contains("Jan");

    }

    @Test
    public void shouldFindEmployeesByRole_2() {
        Employee employee1 = anEmployee().id(1).name("Jan", "Wokalski").role(EmployeeRole.CEO);
        Employee employee2 = new Employee(2, "Adam", "Nowak", EmployeeRole.HR);
        Employee employee3 = new Employee(3, "Anna", "Nowacka", EmployeeRole.HR);
        List<Employee> employeesAsList = new ArrayList<Employee>();
        employeesAsList.add(employee1);
        employeesAsList.add(employee2);
        employeesAsList.add(employee3);
        Employees employees = new Employees(employeesAsList);

        List<Employee> hrEmployees = employees.findByRole(EmployeeRole.HR);

        assertEquals(2, hrEmployees.size());
        assertTrue(hrEmployees.contains(employee2));
        assertTrue(hrEmployees.contains(employee3));
    }

    @Test
    public void shouldCreateEmployeeWithName_expectedObject() {
        Employees employees = new Employees();

        Employee newEmployee = employees.createWithName("Adam", "Nowak");

        assertEquals(new EmployeeIgnoringId("Adam", "Nowak"), newEmployee);
    }

    private <T> List<T> list(T... elements) {
        return Arrays.stream(elements).collect(Collectors.toList());
    }

    public class EmployeeIgnoringId {

        private String givenName;
        private String surname;

        public EmployeeIgnoringId(String givenName, String surname) {
            this.givenName = givenName;
            this.surname = surname;
        }

        @Override
        public boolean equals(Object obj) {
            Employee other = (Employee) obj;
            return new EqualsBuilder()
                    .append(givenName, other.givenName)
                    .append(surname, other.surname)
                    .isEquals();
        }

        @Override
        public int hashCode() {
            return new HashCodeBuilder().append(givenName).append(surname).toHashCode();
        }

        @Override
        public String toString() {
            return ReflectionToStringBuilder.toString(this, ToStringStyle.SIMPLE_STYLE);
        }
    }

}
