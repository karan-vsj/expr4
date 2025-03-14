package com.examly.springapp.controller;

import com.examly.springapp.model.Employee;
import com.examly.springapp.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee) {
        try {
            Employee savedEmployee = employeeService.addEmployee(employee);
            return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees() {
        List<Employee> employees = employeeService.getAllEmployees();
        if (employees.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable int id) {
        Optional<Employee> employee = employeeService.getEmployeeById(id);
        return employee.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/hired/{hireDate}")
    public ResponseEntity<List<Employee>> getEmployeesByHireDate(@PathVariable String hireDate) {
        try {
            Date parsedDate = new SimpleDateFormat("yyyy-MM-dd").parse(hireDate);
            List<Employee> employees = employeeService.getEmployeesByHireDate(parsedDate);
            if (employees.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(employees, HttpStatus.OK);
        } catch (ParseException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/first-three-characters-of-name")
    public ResponseEntity<List<String>> getFirstThreeCharactersOfNames() {
        List<String> firstThreeChars = employeeService.getFirstThreeCharactersOfNames();
        if (firstThreeChars.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(firstThreeChars, HttpStatus.OK);
    }

}
