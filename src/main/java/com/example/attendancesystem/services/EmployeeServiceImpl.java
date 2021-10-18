package com.example.attendancesystem.services;

import com.example.attendancesystem.models.Employee;
import com.example.attendancesystem.repositories.EmployeeRepository;
import java.util.List;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {

  private EmployeeRepository employeeRepository;

  @Autowired
  public EmployeeServiceImpl(
      EmployeeRepository employeeRepository) {
    this.employeeRepository = employeeRepository;
  }

  @Override
  public List<Employee> getAllEmployees() {
    return employeeRepository.findAll();
  }

  @Override
  public Employee getEmployeeById(Long id) {
    return employeeRepository.getById(id);
  }

  @Override
  public void switchAtWork(Employee employee) {
    employee.setAtWork(!employee.isAtWork());
    employeeRepository.save(employee);
  }

  @PostConstruct
  public void createEmployee() {
    employeeRepository.save(new Employee("Tomáš", "Dělník"));
  }
}
