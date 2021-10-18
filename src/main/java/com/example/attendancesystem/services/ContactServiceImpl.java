package com.example.attendancesystem.services;

import com.example.attendancesystem.models.Contact;
import com.example.attendancesystem.models.Employee;
import com.example.attendancesystem.repositories.ContactRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactServiceImpl implements ContactService{

  private ContactRepository contactRepository;

  @Autowired
  public ContactServiceImpl(
      ContactRepository contactRepository) {
    this.contactRepository = contactRepository;
  }


  @Override
  public Contact getEmployeesContact(Employee employee) {
    return contactRepository.getContactByEmployee(employee);
  }
}
