package com.examly.springapp.service;

public class EmployeeService {

}
package com.examly.springapp.service;
import com.examly.springapp.model.Employee;
import com.examly.springapp.repository.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeService{
    @Autowired
    private EmployeeRepo employeeRepo;
    public Employee addEmployee(Employee employee) {
        return employeeRepo.save(employee);
    }
    public List<Employee> getAllEmployees() {
        return employeeRepo.findAll();
    }
    public Optional<Employee> getEmployeeById(int id) {
        return employeeRepo.findById(id);
    }
    public List<Employee> getEmployeesByHireDate(Date hireDate) {
        return employeeRepo.findByHireDate(hireDate);
    }
    public List<String> getFirstThreeCharactersOfNames() {
        return employeeRepo.findAll().stream().map(employee -> employee.getName().substring(0, Math.min(3, employee.getName().length()))).collect(Collectors.toList());
    }
}

