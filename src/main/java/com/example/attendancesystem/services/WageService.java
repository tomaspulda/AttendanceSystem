package com.example.attendancesystem.services;

import com.example.attendancesystem.models.Employee;
import com.example.attendancesystem.models.Wage;

public interface WageService {

  void calculateWage(Employee employee, String month, int bonus);

  int taxCredit();

  Wage createWage(Employee employee, String month, int bonus);

  Wage getWage(Employee employee, String month);
}
