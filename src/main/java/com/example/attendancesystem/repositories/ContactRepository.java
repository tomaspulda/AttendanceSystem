package com.example.attendancesystem.repositories;

import com.example.attendancesystem.models.Contact;
import com.example.attendancesystem.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {

  Contact getContactByEmployee(Employee employee);
}