package com.room.reservation.program.service;

import com.room.reservation.program.model.Employee;
import com.room.reservation.program.repository.EmployeeRepository;

import java.io.IOException;


public class EmployeeServiceImpl implements EmployeeService{

    private EmployeeRepository employeeRepository;

    public EmployeeServiceImpl() {
        this.employeeRepository = new EmployeeRepository();
    }

    @Override
    public boolean isEmployeeExisting(Long employeeNumber) throws IOException {
        return employeeRepository.getEmployee( employeeNumber ).isPresent();
    }

    @Override
    public boolean isEmployeeExisting(Long employeeNumber, String employeePassword) throws IOException {
        return employeeRepository.getEmployee( new Employee(employeeNumber, employeePassword) ).isPresent();
    }

    @Override
    public void createEmployee(Employee employee) {
        try {
            employeeRepository.save(employee);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteEmployee(Employee employee) throws IOException {
        if(isEmployeeExisting(employee.getEmployeeNumber())) {
            employeeRepository.delete(employee);
        }
    }
}
