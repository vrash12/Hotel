package com.room.reservation.program.model;

import java.util.Objects;

public class Employee {

    private Long employeeNumber;

    private String employeePassword;

    private String employeeFullName;

    public Employee(Long employeeNumber) {
        this( employeeNumber, null, null );
    }


    public Employee(Long employeeNumber, String employeePassword) {
        this( employeeNumber, null, employeePassword );
    }

    public Employee(Long employeeNumber, String employeeFullName, String employeePassword ) {
        this.employeeNumber = employeeNumber;
        this.employeePassword = employeePassword;
        this.employeeFullName = employeeFullName;
    }

    public Long getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(Long employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    public String getEmployeePassword() {
        return employeePassword;
    }

    public void setEmployeePassword(String employeePassword) {
        this.employeePassword = employeePassword;
    }


    public String getEmployeeFullName() {
        return employeeFullName;
    }

    public void setEmployeeFullName(String employeeFullName) {
        this.employeeFullName = employeeFullName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(employeeNumber, employee.employeeNumber) &&
                Objects.equals(employeePassword, employee.employeePassword);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employeeNumber, employeePassword);
    }

    @Override
    public String toString() {
        return employeeNumber + "," + employeeFullName + "," + employeePassword;
    }
}


