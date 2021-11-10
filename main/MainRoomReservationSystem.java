package com.room.reservation.program.main;

import com.room.reservation.program.exception.EmployeeNotFoundException;
import com.room.reservation.program.model.Employee;
import com.room.reservation.program.service.EmployeeService;
import com.room.reservation.program.service.EmployeeServiceImpl;

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class MainRoomReservationSystem {

    private static final String MAIN_MENU = "Hotel California\n1. Reserve Room\n2. Check Availability of Room\n3. Cancel Reservation\n0. Exit\n";

    private static EmployeeService employeeService = new EmployeeServiceImpl();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean isRunning = true;
        do {
            System.out.println(MAIN_MENU);
            int opt = scanner.nextInt();
            switch(opt) {
                case 1:

                    break;
                case 2:

                    break;
                case 3:
                    break;
                case 0:
                    isRunning = false;
                    break;
                default:
                    System.err.println("Please select from 0-3 only.");
                    break;
            }
        }while( isRunning );
    }

    private static void createEmployee( ) {
        Scanner sc= new Scanner(System.in);
        System.out.print("Input Employee Full Name: ");
        String employeeName = sc.nextLine();

        System.out.print("Input Employee ID number: ");
        long employeeNumber = sc.nextLong();

        sc.nextLine();
        System.out.print("Input Employee password: ");
        String password = sc.nextLine();

        employeeService.createEmployee( new Employee(employeeNumber, employeeName, password));
    }

    private static void checkForEmployeeCredentials() throws EmployeeNotFoundException, IOException {
        Scanner sc= new Scanner(System.in);
        System.out.print("Input Employee ID number: ");
        long employeeNumber = sc.nextLong();
        sc.nextLine();
        System.out.print("Input Employee password: ");
        String password = sc.nextLine();

        if( !employeeService.isEmployeeExisting( employeeNumber, password ) ) {
            throw new EmployeeNotFoundException("ID number or password is not found");
        }

    }

    private static void deleteEmployee() throws IOException, EmployeeNotFoundException {
        Scanner sc= new Scanner(System.in);
        System.out.print("Input Employee ID number: ");
        long employeeNumber = sc.nextLong();
        sc.nextLine();
        System.out.print("Are you sure you want to delete the data of this employee? (Y or N): ");
        String choice = sc.nextLine();

        while( !Arrays.asList( "y", "n" ).contains( choice.toLowerCase() ) ) {
            System.out.println("Please input either Y or N only");
            System.out.print("Are you sure you want to delete the data of this employee? (Y or N): ");
            choice = sc.nextLine();
        }

        switch( choice.trim() ) {
            case "Y": case "y":
                if( !employeeService.isEmployeeExisting( employeeNumber ) ) {
                    throw new EmployeeNotFoundException("Employee is not found");
                }

                employeeService.deleteEmployee( new Employee(employeeNumber) );
            break;
        }
    }




}
