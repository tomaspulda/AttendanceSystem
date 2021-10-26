package com.example.attendancesystem.services;

import com.example.attendancesystem.models.Employee;
import com.example.attendancesystem.models.Wage;

public interface WageService {

  void calculateWage(Wage wage);

  int calculateTax(Wage wage);

  Wage createWage(Employee employee, String month, int bonus, int kids, boolean spouse,
      boolean handicappedI, boolean handicappedII_III, boolean physicalDisability,
      boolean student);

  Wage getWage(Employee employee, String month);
}
