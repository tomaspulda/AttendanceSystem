package com.example.attendancesystem.services;

import com.example.attendancesystem.models.Contact;
import com.example.attendancesystem.models.Employee;

public interface ContactService {

  Contact getEmployeesContact(Employee employee);

  void createNewContact(String street, int houseNumber, String city, int postCode, String country, Long phoneNumber, Long employeeId)
      throws Exception;
}
