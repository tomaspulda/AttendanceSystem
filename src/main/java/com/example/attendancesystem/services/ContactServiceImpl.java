package com.example.attendancesystem.services;

import com.example.attendancesystem.models.Contact;
import com.example.attendancesystem.models.Employee;
import com.example.attendancesystem.repositories.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactServiceImpl implements ContactService{

  private ContactRepository contactRepository;
  private EmployeeService employeeService;

  @Autowired
  public ContactServiceImpl(
      ContactRepository contactRepository,
      EmployeeService employeeService) {
    this.contactRepository = contactRepository;
    this.employeeService = employeeService;
  }


  @Override
  public Contact getEmployeesContact(Employee employee) {
    return contactRepository.getContactByEmployee(employee);
  }

  @Override
  public void createNewContact(String street, int houseNumber, String city, int postCode, String country, Long phoneNumber, Long employeeId)
      throws Exception {
    contactRepository.save(new Contact(street, houseNumber, city, postCode, country, phoneNumber,
        employeeService.getEmployeeById(employeeId)));
  }
}
