package com.ds.spring.boot.hazelcast;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Slf4j
@Component
@CacheConfig(cacheNames = "employees")
public class EmployeeDAO {


   @Cacheable()
    public List<Employee> fetchEmployee() {
        List<Employee> employees = getEmployees();

        return employees;
    }

    private List<Employee> getEmployees() {
        log.info("**** Calling ****** EmployeeDAO->getEmployees()");
        Employee emp = new Employee();
        emp.setEmpId("1234");
        emp.setEmpName("Dhanasekaran Subramani");

        Employee emp1 = new Employee();
        emp1.setEmpId("5678");
        emp1.setEmpName("Lakshanya");

        Employee emp2 = new Employee();
        emp2.setEmpId("90123");
        emp2.setEmpName("Viswanth");

        List<Employee> employees = new ArrayList<>();
        employees.add(emp1);
        employees.add(emp2);
        return employees;
    }
}
