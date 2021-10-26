package com.example.attendancesystem.services;

import com.example.attendancesystem.models.Employee;
import com.example.attendancesystem.models.WorkedHours;
import com.example.attendancesystem.repositories.WorkedHoursRepository;
import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WorkedHoursServiceImpl implements WorkedHoursService {

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
    workedHoursRepository.save(new WorkedHours(new Date(), LocalTime.now(), employee));
    employeeService.switchAtWork(employee);
  }

  @Override
  public void setEnd(Employee employee) {
    WorkedHours workedHours = findLast(employee);
    workedHours.setEnd(LocalTime.now());
    if (workedHours.getStart().isBefore(workedHours.getEnd())) {
      workedHours.setHoursWorked(
          (double) Duration.between(workedHours.getStart(), workedHours.getEnd()).toMinutes());
    } else {
      workedHours.setHoursWorked(
          (double) (Duration.between(workedHours.getStart(), LocalTime.of(23, 59, 59)).toMinutes()
              + Duration.between(LocalTime.MIDNIGHT, workedHours.getEnd()).toMinutes())
      );

    }
    workedHoursRepository.save(workedHours);
    employeeService.switchAtWork(employee);
  }

  @Override
  public WorkedHours findLast(Employee employee) {
    return workedHoursRepository.getByEmployeeOrderByIdDesc(employee).get(0);
  }

  @Override
  public List<WorkedHours> getAllByEmployee(Employee employee) {
    return workedHoursRepository.getByEmployeeOrderByDateDesc(employee);
  }

  @Override
  public int getMonthByEmployee(Employee employee, String month) {
    List<WorkedHours> workedHoursList = workedHoursRepository
        .findAllByEmployeeAndMonth(employee, month);
    int hoursWorkedInMonth = 0;
    for (WorkedHours workedHours : workedHoursList) {
      hoursWorkedInMonth += workedHours.getHoursWorked();
    }
    return hoursWorkedInMonth;
  }

  @Override
  public List<String> getEmployeesMonths(Employee employee) {
    List<WorkedHours> workedHoursList = workedHoursRepository.findAllByEmployee(employee);
    List<String> integerList = new ArrayList<>();
    for (WorkedHours hours : workedHoursList) {
      if (!integerList.contains(hours.getMonth())) {
        integerList.add(hours.getMonth());
      }
    }
    return integerList;
  }
}