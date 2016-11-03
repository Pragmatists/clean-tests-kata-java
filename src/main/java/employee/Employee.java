package employee;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class Employee {

    EmployeeRole employeeRole;
    int id;
    public String givenName;
    public String surname;

    public Employee(int id, String givenName, String surname, EmployeeRole employeeRole) {
        this.id = id;
        this.givenName = givenName;
        this.surname = surname;
        this.employeeRole = employeeRole;
    }

    public Employee() {
        // TODO Auto-generated constructor stub
    }

    public boolean hasRole(EmployeeRole employeeRole) {
        return this.employeeRole.equals(employeeRole);
    }

    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

    public int getId() {
        return id;
    }

    public String getGivenName() {
        return givenName;
    }

    public static Employee anEmployee() {
        return new Employee();
    }

    public Employee id(int id) {
        this.id = id;
        return this;
    }

    public Employee name(String givenName, String surname) {
        this.givenName = givenName;
        this.surname = surname;
        return this;
    }

    public Employee role(EmployeeRole employeeRole) {
        this.employeeRole = employeeRole;
        return this;
    }
}
