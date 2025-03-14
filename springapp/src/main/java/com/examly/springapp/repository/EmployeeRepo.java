
package com.examly.springapp.repository;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.examly.springapp.model.Employee;

public interface EmployeeRepo extends JpaRepository<Employee,Integer>{
    List<Employee> findByHireDate(Date hireDate);
}

