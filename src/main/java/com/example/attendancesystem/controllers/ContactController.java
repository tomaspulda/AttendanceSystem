package com.example.attendancesystem.controllers;

import com.example.attendancesystem.services.ContactService;
import com.example.attendancesystem.services.EmployeeService;
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
@RequestMapping("/contact")
public class ContactController {

  private EmployeeService employeeService;
  private WorkedHoursService workedHoursService;
  private ContactService contactService;

  @Autowired
  public ContactController(EmployeeService employeeService,
      WorkedHoursService workedHoursService,
      ContactService contactService) {
    this.employeeService = employeeService;
    this.workedHoursService = workedHoursService;
    this.contactService = contactService;
  }

  @GetMapping("/new/{employeeId}")
  public String getNewContactForm(@PathVariable Long employeeId, Model model) {
    model.addAttribute("eId", employeeId);
    return "newcontact";
  }

  @PostMapping("/new/{employeeId}")
  public String createNewContact(@PathVariable Long employeeId, @RequestParam String street,
      @RequestParam int houseNumber,
      @RequestParam String city, @RequestParam int postCode, @RequestParam String country,
      @RequestParam Long phoneNumber) throws Exception {
    contactService
        .createNewContact(street, houseNumber, city, postCode, country, phoneNumber, employeeId);
    return "redirect:/employee/";
  }

}
