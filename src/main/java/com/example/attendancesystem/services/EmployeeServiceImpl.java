package com.example.attendancesystem.services;

import com.example.attendancesystem.models.Employee;
import com.example.attendancesystem.repositories.EmployeeRepository;
import java.util.List;
import java.util.Optional;
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
  public Employee getEmployeeById(Long id) throws Exception {
    Optional<Employee> optionalEmployee = employeeRepository.findById(id);
    if (optionalEmployee.isEmpty()) {
      throw new Exception("There is no Employee");
    }
    return optionalEmployee.get();
  }

  @Override
  public void switchAtWork(Employee employee) {
    employee.setAtWork(!employee.isAtWork());
    employeeRepository.save(employee);
  }
}
