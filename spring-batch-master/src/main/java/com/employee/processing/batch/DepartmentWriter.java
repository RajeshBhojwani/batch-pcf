package com.employee.processing.batch;

import org.springframework.batch.item.ItemWriter;

import java.util.ArrayList;
import java.util.List;

public class DepartmentWriter implements ItemWriter<Employee> {
    @Override
    public void write(List<? extends Employee> items) throws Exception {
        List<String> employeeList = new ArrayList<>();
        items.forEach(item -> {
            String enrichedTxn = String.join(",", item.getId(), item.getEmployeeNumber(), item.getSalary(),
                    item.getDepartment());
            employeeList.add(enrichedTxn);
        });
        employeeList.forEach(System.out::println);

    }
}
