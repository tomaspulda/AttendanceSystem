package com.example.attendancesystem.controllers;

import com.example.attendancesystem.models.Employee;
import com.example.attendancesystem.services.EmployeeService;
import com.example.attendancesystem.services.WageService;
import com.example.attendancesystem.services.WorkedHoursService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/wage")
public class WageController {

  private WageService wageService;
  private WorkedHoursService workedHoursService;
  private EmployeeService employeeService;

  @Autowired
  public WageController(WageService wageService,
      WorkedHoursService workedHoursService,
      EmployeeService employeeService) {
    this.wageService = wageService;
    this.workedHoursService = workedHoursService;
    this.employeeService = employeeService;
  }

  @GetMapping("/{employee_id}")
  public String getWageCalculator(@PathVariable Long employee_id, Model model) throws Exception {
    model.addAttribute("months",
        workedHoursService.getEmployeesMonths(employeeService.getEmployeeById(employee_id)));
    model.addAttribute("employee", employee_id);
    return "wagecalculator";
  }

  @PostMapping("/calculator/{employee_id}")
  public String calculateWage(@PathVariable Long employee_id, @RequestParam String month,
      @RequestParam int bonus)
      throws Exception {
    Employee employee = employeeService.getEmployeeById(employee_id);
    wageService.calculateWage(employee, month, bonus);

    return "redirect:/wage/" + employee_id + "/" + month;
  }

  @GetMapping("/{employee_id}/{month}")
  public String getWage(Model model, @PathVariable Long employee_id, @PathVariable String month)
      throws Exception {
    Employee employee = employeeService.getEmployeeById(employee_id);
    model.addAttribute("wage", wageService.getWage(employee, month));
    model.addAttribute("employee", employee);
    return "wage";
  }
}