package $2_assertions.custom;

import employee.Employee;
import employee.EmployeeRole;
import employee.Employees;
import org.assertj.core.api.ObjectAssert;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class EmployeesAssert extends ObjectAssert<Employees> {

    public EmployeesAssert(Employees actual) {
        super(actual);
    }

    public static EmployeesAssert on(List<Employee> employees) {
        return new EmployeesAssert(new Employees(employees));
    }

    public void containsOnlyEmployeesOfRole(EmployeeRole role) {
        assertThat(actual).allMatch(e -> e.hasRole(role));
    }

}
