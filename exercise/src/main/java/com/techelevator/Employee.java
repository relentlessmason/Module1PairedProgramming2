package com.techelevator;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Employee {
    private long employeeId;
    private String firstName;
    private String lastName;
    private String email;
    private Department department;
    private LocalDate hireDate;
    private final double STARTING_SALARY = 60000.0;
    private double salary;

    public long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(long employeeId) {
        this.employeeId = employeeId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public LocalDate getHireDate() {
        return hireDate;
    }

    public void setHireDate(LocalDate hireDate) {
        this.hireDate = hireDate;
    }

    public Employee(long employeeId, String firstName, String lastName, String email, Department department, LocalDate hireDate) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.department = department;
        this.hireDate = hireDate;
        this.salary = STARTING_SALARY;
    }

    public Employee() {
        this.salary = STARTING_SALARY;
    }

    public String getFullName() {
        return getLastName() + ", " + getFirstName();
    }

    public void raiseSalary(double percent) {
        BigDecimal percentBD = new BigDecimal(percent).movePointLeft(2);
        this.salary += percentBD.multiply(BigDecimal.valueOf(salary)).doubleValue();
    }
}
