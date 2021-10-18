package com.example.attendancesystem.controllers;

import com.example.attendancesystem.services.EmployeeService;
import com.example.attendancesystem.services.WorkedHoursService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class HomeController {

  private EmployeeService employeeService;
  private WorkedHoursService workedHoursService;

  @Autowired
  public HomeController(EmployeeService employeeService,
      WorkedHoursService workedHoursService) {
    this.employeeService = employeeService;
    this.workedHoursService = workedHoursService;
  }

  @GetMapping("/")
  public String getAllEmployees(Model model) {
    model.addAttribute("employees", employeeService.getAllEmployees());
    return "employees";
  }

  @GetMapping("/employee/start/{employee_id}")
  public String setShiftStart(@PathVariable Long employee_id) {
    workedHoursService.setStart(employeeService.getEmployeeById(employee_id));
    return "redirect:/";
  }

  @GetMapping("/employee/end/{employee_id}")
  public String setShiftEnd(@PathVariable Long employee_id) {
    workedHoursService.setEnd(employeeService.getEmployeeById(employee_id));
    return "redirect:/";
  }
}