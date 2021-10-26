package com.example.attendancesystem.repositories;

import com.example.attendancesystem.models.Contact;
import com.example.attendancesystem.models.Employee;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {

  List<Contact> findAllByEmployee(Employee employee);
}