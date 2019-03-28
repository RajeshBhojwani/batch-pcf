package com.employee.processing.batch;

import org.springframework.batch.item.ItemProcessor;


public class DepartmentProcessor implements ItemProcessor<Employee, Employee> {
    @Override
    public Employee process(Employee item) throws Exception {

        if ("1001".equalsIgnoreCase(item.getEmployeeNumber())) {
            item.setDepartment("Sales");
        } else if ("1002".equalsIgnoreCase(item.getEmployeeNumber())) {
            item.setDepartment("IT");
        } else {
            item.setDepartment("Staff");
        }
        System.out.println("Employee Details --> " + item.toString());
        return item;
    }
}
