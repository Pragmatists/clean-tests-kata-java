package employee;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Employees implements Iterable<Employee> {

    List<Employee> employeesAsList;

    public Employees(List<Employee> employeesAsList) {
        this.employeesAsList = employeesAsList;
    }

    public Employees() {
        this(Collections.emptyList());
    }

    public List<Employee> findByRole(EmployeeRole employeeRole) {
        return employeesAsList.stream().filter(employee -> employee.hasRole(employeeRole)).collect(Collectors.toList());
    }

    public Employee findById(int id) {
        for (Employee employee : employeesAsList) {
            if (employee.id == id) {
                return employee;
            }
        }
        throw new EmployeeNotFoundException();
    }

    public int size() {
        return employeesAsList.size();
    }

    public boolean contains(Employee employee) {
        return employeesAsList.contains(employee);
    }

    public void remove(int id) {
        employeesAsList.remove(findById(id));
    }

    public Employee createWithName(String givenName, String surname) {
        return new Employee(nextRandomId(), givenName, surname, EmployeeRole.DEFAULT);
    }

    private int nextRandomId() {
        return new Random(System.currentTimeMillis()).nextInt(100000);
    }

    @Override
    public Iterator<Employee> iterator() {
        return employeesAsList.iterator();
    }

}
