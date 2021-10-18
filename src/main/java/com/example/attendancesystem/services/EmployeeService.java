package com.example.attendancesystem.services;

import com.example.attendancesystem.models.Employee;
import java.util.List;

public interface EmployeeService {

  List<Employee> getAllEmployees();

  Employee getEmployeeById(Long id);

}
