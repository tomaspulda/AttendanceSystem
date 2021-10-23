package com.example.attendancesystem.controllers;

import com.example.attendancesystem.services.ContactService;
import com.example.attendancesystem.services.EmployeeService;
import com.example.attendancesystem.services.WorkedHoursService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

  private EmployeeService employeeService;
  private WorkedHoursService workedHoursService;
  private ContactService contactService;

  @Autowired
  public HomeController(EmployeeService employeeService,
      WorkedHoursService workedHoursService,
      ContactService contactService) {
    this.employeeService = employeeService;
    this.workedHoursService = workedHoursService;
    this.contactService = contactService;
  }

  @GetMapping("/")
  public String home() {
    return "index";
  }
}