package com.example.demo.controller;
import com.example.demo.model.Employee;
import com.example.demo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/employee")
    public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/add")
    public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee) throws Exception{
        Employee employeeSavedToDB = this.employeeService.addEmployee(employee);
        return new ResponseEntity<Employee>(employeeSavedToDB, HttpStatus.CREATED);
    }

    @GetMapping("/hello")
    // Easy method just to print encouraging and consoling
    // words
    public String hello()
    {
        return "Hello Geek, this is a simple hello message to take care and have a nice day.";
    }

    @GetMapping("/auth/greet")
    // Easy method just to print greeting message by saying
    // spring-based applications
    public String greet()
    {
        return "Hello Geek, Fast and easy development can be possible on Spring-based applications by reducing source code;.";
    }

    }


