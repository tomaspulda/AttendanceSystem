package com.example.attendancesystem.services;

import com.example.attendancesystem.models.Contact;
import com.example.attendancesystem.models.Employee;
import java.util.List;

public interface ContactService {

  Contact getEmployeesContact(Employee employee);

}
