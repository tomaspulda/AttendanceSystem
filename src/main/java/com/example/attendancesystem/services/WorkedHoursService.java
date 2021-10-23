package com.example.attendancesystem.services;

import com.example.attendancesystem.models.Employee;
import com.example.attendancesystem.models.WorkedHours;
import java.util.List;

public interface WorkedHoursService {

  void setStart(Employee employee);

  void setEnd(Employee employee);

  WorkedHours findLast(Employee employee);

  List<WorkedHours> getAllByEmployee(Employee employee);

}