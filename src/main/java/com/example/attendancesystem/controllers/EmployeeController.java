package com.example.attendancesystem.controllers;

import com.example.attendancesystem.models.Employee;
import com.example.attendancesystem.services.ContactService;
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
@RequestMapping("/employee")
public class EmployeeController {

  private EmployeeService employeeService;
  private WorkedHoursService workedHoursService;
  private ContactService contactService;
  private WageService wageService;

  @Autowired
  public EmployeeController(EmployeeService employeeService,
      WorkedHoursService workedHoursService,
      ContactService contactService, WageService wageService) {
    this.employeeService = employeeService;
    this.workedHoursService = workedHoursService;
    this.contactService = contactService;
    this.wageService = wageService;
  }

  @GetMapping("/")
  public String getAllEmployees(Model model) {
    model.addAttribute("employees", employeeService.getAllEmployees());
    return "employees";
  }

  @GetMapping("/start/{employee_id}")
  public String setShiftStart(@PathVariable Long employee_id) throws Exception {
    workedHoursService.setStart(employeeService.getEmployeeById(employee_id));
    return "redirect:/employee/";
  }

  @GetMapping("/end/{employee_id}")
  public String setShiftEnd(@PathVariable Long employee_id) throws Exception {
    workedHoursService.setEnd(employeeService.getEmployeeById(employee_id));
    return "redirect:/employee/";
  }

  @GetMapping("/detail/{employee_id}")
  public String getEmployeeDetail(@PathVariable Long employee_id, Model model) throws Exception {
    model.addAttribute("employee", employeeService.getEmployeeById(employee_id));
    model.addAttribute("contact", contactService.getEmployeesContact(
        employeeService.getEmployeeById(employee_id)));
    model.addAttribute("shifts", workedHoursService.getAllByEmployee(
        employeeService.getEmployeeById(employee_id)));
    model.addAttribute("wages", wageService.getAllEmployeesWages(employee_id));
    return "employeedetail";
  }

  @GetMapping("/new")
  public String getNewEmployeeForm() {
    return "newemployee";
  }

  @PostMapping("/new")
  public String createNewEmployee(@RequestParam String name, @RequestParam String dateOfBirth,
      @RequestParam String dateOfStart, @RequestParam String position,
      @RequestParam int hourlyWage) {
    Employee employee = new Employee(name, dateOfBirth, position, dateOfStart, hourlyWage);
    employeeService.createEmployee(employee);
    Long id = employee.getId();
    return "redirect:/contact/new/" + id;
  }

  @GetMapping("/delete/{employeeId}")
  public String deleteEmployee(@PathVariable Long employeeId) throws Exception {
    employeeService.deleteEmployee(employeeService.getEmployeeById(employeeId));
    return "redirect:/employee/";
  }

  @GetMapping("/edit/{employeeId}")
  public String getEditEmployeeForm(@PathVariable Long employeeId, Model model) throws Exception {
    Employee employee = employeeService.getEmployeeById(employeeId);
    model.addAttribute("employee", employee);
    return "editemployee";
  }

  @PostMapping("/edit/{employeeId}")
  public String editEmployee(@PathVariable Long employeeId, @RequestParam String name,
      @RequestParam String position, @RequestParam String dateOfBirth,
      @RequestParam String dateOfStart, @RequestParam int hourlyWage) throws Exception {
    Employee employee = employeeService.getEmployeeById(employeeId);
    employee.setName(name);
    employee.setPosition(position);
    employee.setDayOfBirth(dateOfBirth);
    employee.setDateOfStart(dateOfStart);
    employee.setHourlyWage(hourlyWage);
    employeeService.editEmployee(employee);
    return "redirect:/employee/";
  }

  @PostMapping("/search")
  public String searchEmployee(@RequestParam String keyword, Model model) {
    model.addAttribute("employees", employeeService.searchEmployee(keyword));
    return "employees";
  }
}