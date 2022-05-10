package com.hibernate;

import com.hibernate.models.Address;
import com.hibernate.models.Employee;
import com.hibernate.services.EmployeeService;
import lombok.extern.java.Log;

@Log
public class MainRunner {
    public static void main(String[] args) {
        EmployeeService employeeService = new EmployeeService();

        Employee e1 = new Employee("jafer", 70000);
        Address a1 = new Address("123 st", "", "plano", 75000, e1);

        employeeService.createEmployee(new Employee("john", 60000));
        employeeService.createEmployee(new Employee("jane", 50000));

        boolean saved = employeeService.createEmployeeAndAddress(a1);

        // e1.setId(233);
        e1.setName("someone else");
        e1.setSalary(101010);
        boolean updated = employeeService.updateEmployee(e1);
        log.info("Saved: "+Boolean.toString(saved));
        log.info("Updated: "+Boolean.toString(updated));
        log.info(employeeService.getAllEmployees().toString());
        log.info(employeeService.getEmployeeById(1).toString());
        log.info(employeeService.findEmployeeSalaryGreaterThan(100000).toString());
        log.info(Boolean.toString(employeeService.deleteEmployee(employeeService.getEmployeeById(1))));
        log.info(employeeService.findEmployeeAddresses(e1).toString());
        log.info(employeeService.EmployeeIdAndName().toString());
        Address a5 = new Address("555 st", "", "seattle", 98109, new Employee());
        employeeService.addAddress(a5,3);
        log.info(employeeService.findEmployeeAddresses(e1).toString());
        Address a6 = new Address("252 st", "", "seattle", 98109, new Employee());
        employeeService.addAddress(a6,2);
        log.info(employeeService.findEmployeeAddresses(new Employee(2,"",12)).toString());

    } // main method
} // MainRunner
