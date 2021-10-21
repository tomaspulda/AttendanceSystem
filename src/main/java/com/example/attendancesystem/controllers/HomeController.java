package com.example.attendancesystem.controllers;

import com.example.attendancesystem.services.ContactService;
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
  public String getAllEmployees(Model model) {
    model.addAttribute("employees", employeeService.getAllEmployees());
    return "employees";
  }

  @GetMapping("/employee/start/{employee_id}")
  public String setShiftStart(@PathVariable Long employee_id) throws Exception {
    workedHoursService.setStart(employeeService.getEmployeeById(employee_id));
    return "redirect:/";
  }

  @GetMapping("/employee/end/{employee_id}")
  public String setShiftEnd(@PathVariable Long employee_id) throws Exception {
    workedHoursService.setEnd(employeeService.getEmployeeById(employee_id));
    return "redirect:/";
  }

  @GetMapping("/employee/detail/{employee_id}")
  public String getEmployeeDetail(@PathVariable Long employee_id, Model model) throws Exception {
    model.addAttribute("employee", employeeService.getEmployeeById(employee_id));
    model.addAttribute("contact", contactService.getEmployeesContact(
        employeeService.getEmployeeById(employee_id)));
    return "detail";
  }
}