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

  @Autowired
  public WorkedHoursServiceImpl(
      WorkedHoursRepository workedHoursRepository) {
    this.workedHoursRepository = workedHoursRepository;
  }

  @Override
  public void setStart(Employee employee) {
    workedHoursRepository.save(new WorkedHours(new Date(), Instant.now(), employee));
  }

  @Override
  public void setEnd(Employee employee) {
    WorkedHours workedHours = findLast(employee);
    workedHours.setEnd(Instant.now());
    workedHours.setHoursWorked(Duration.between(workedHours.getStart(), workedHours.getEnd()).toHours());
    workedHoursRepository.save(workedHours);

  }

  @Override
  public WorkedHours findLast(Employee employee) {
    return workedHoursRepository.getByEmployeeOrderByIdDesc(employee).get(0);
  }
}
