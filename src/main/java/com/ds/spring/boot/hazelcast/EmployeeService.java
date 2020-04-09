package com.ds.spring.boot.hazelcast;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private EmployeeDAO employeeDAO;

    public EmployeeService(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }


    public List<Employee> getAllEmployee() {
        return employeeDAO.fetchEmployee();
    }
}
