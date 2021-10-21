package com.example.attendancesystem.services;

import com.example.attendancesystem.models.Employee;
import java.util.List;
import java.util.Optional;

public interface EmployeeService {

  List<Employee> getAllEmployees();

  Employee getEmployeeById(Long id) throws Exception;

  void switchAtWork(Employee employee);

}
