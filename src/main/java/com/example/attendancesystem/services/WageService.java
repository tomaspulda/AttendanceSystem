package com.example.attendancesystem.services;

import com.example.attendancesystem.models.Employee;
import com.example.attendancesystem.models.Wage;
import java.util.List;

public interface WageService {

  void calculateWage(Wage wage);

  int calculateTax(Wage wage);

  void createWage(int bonus, String month, int kids, boolean spouse, boolean handicappedI,
      boolean handicappedII_III, boolean physicalDisability, boolean student,
      Employee employee);

  Wage getWage(Employee employee, String month);

  List<Wage> getAllEmployeesWages(Long id);
}
