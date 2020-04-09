package com.ds.spring.boot.hazelcast;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class HazelCastTest {

    @Autowired
    private EmployeeService employeeService;

    @Test
   void getAllEmployee()
    {
        employeeService.getAllEmployee();
        employeeService.getAllEmployee();
        employeeService.getAllEmployee();

    }
}

