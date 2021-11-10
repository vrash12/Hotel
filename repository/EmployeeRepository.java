package com.room.reservation.program.repository;

import com.room.reservation.program.constants.FileConstants;
import com.room.reservation.program.mapper.EmployeeMapper;
import com.room.reservation.program.model.Employee;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EmployeeRepository {

    public Optional<Employee> getEmployee( final Employee potentialEmployee ) throws IOException {
        Stream<Employee> employees = Files.lines(Paths.get(FileConstants.EMPLOYEE_LIST_FILE)).map(EmployeeMapper::mapFromText)
                .filter( e -> e.equals(potentialEmployee) );
        return employees.findFirst();

    }

    public Optional<Employee> getEmployee( final Long employeeNumber ) throws IOException {
        return Files.lines(Paths.get(FileConstants.EMPLOYEE_LIST_FILE)).map(EmployeeMapper::mapFromText)
                .filter( e -> e.getEmployeeNumber().longValue() == employeeNumber ).findFirst();

    }

    public void save(Employee employee) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File(FileConstants.EMPLOYEE_LIST_FILE)));
        bufferedWriter.write(employee.toString());
        bufferedWriter.newLine();
        bufferedWriter.close();

    }

    public void delete(Employee employee) throws IOException {
        List<Employee> remainingEmployees = Files.lines(Paths.get(FileConstants.EMPLOYEE_LIST_FILE)).map(EmployeeMapper::mapFromText)
                .filter( e -> e.getEmployeeNumber().longValue() != employee.getEmployeeNumber() ).collect(Collectors.toList());
        remainingEmployees.forEach(System.out::println);
        final BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File(FileConstants.EMPLOYEE_LIST_FILE)));
        remainingEmployees.forEach( e -> {
            try {
                bufferedWriter.write(e.toString());
                bufferedWriter.newLine();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }

        } );
        bufferedWriter.close();
    }
}
