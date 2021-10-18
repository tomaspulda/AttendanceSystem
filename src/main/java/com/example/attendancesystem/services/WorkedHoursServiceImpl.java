package com.example.attendancesystem.services;

import com.example.attendancesystem.models.Employee;
import com.example.attendancesystem.models.WorkedHours;
import com.example.attendancesystem.repositories.WorkedHoursRepository;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WorkedHoursServiceImpl implements WorkedHoursService{

  private WorkedHoursRepository workedHoursRepository;
  private EmployeeService employeeService;

  @Autowired
  public WorkedHoursServiceImpl(
      WorkedHoursRepository workedHoursRepository,
      EmployeeService employeeService) {
    this.workedHoursRepository = workedHoursRepository;
    this.employeeService = employeeService;
  }

  @Override
  public void setStart(Employee employee) {
    workedHoursRepository.save(new WorkedHours(new Date(), Instant.now(), employee));
    employeeService.switchAtWork(employee);
  }

  @Override
  public void setEnd(Employee employee) {
    WorkedHours workedHours = findLast(employee);
    workedHours.setEnd(Instant.now());
    workedHours.setHoursWorked(Duration.between(workedHours.getStart(), workedHours.getEnd()).toMinutes());
    workedHoursRepository.save(workedHours);
    employeeService.switchAtWork(employee);
  }

  @Override
  public WorkedHours findLast(Employee employee) {
    return workedHoursRepository.getByEmployeeOrderByIdDesc(employee).get(0);
  }
}
