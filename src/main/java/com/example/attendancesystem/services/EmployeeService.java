package com.example.attendancesystem.services;

import com.example.attendancesystem.models.Employee;
import java.util.List;

public interface EmployeeService {

  List<Employee> getAllEmployees();

  Employee getEmployeeById(Long id) throws Exception;

  void switchAtWork(Employee employee);

  void createEmployee(Employee employee);

  void deleteEmployee(Employee employee);

  void editEmployee(Employee employee);

}