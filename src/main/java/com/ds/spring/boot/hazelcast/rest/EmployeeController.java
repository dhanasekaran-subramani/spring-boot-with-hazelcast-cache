package com.ds.spring.boot.hazelcast.rest;

import com.ds.spring.boot.hazelcast.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employee/all")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public ResponseEntity getAllEmployess() {

        return new ResponseEntity(employeeService.getAllEmployee(), HttpStatus.OK);

    }

}
