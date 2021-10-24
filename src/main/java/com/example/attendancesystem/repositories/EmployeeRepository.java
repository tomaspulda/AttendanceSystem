package com.example.attendancesystem.repositories;

import com.example.attendancesystem.models.Employee;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

  List<Employee> findAllByOrderByName();

  List<Employee> findAllByNameContainingOrPositionContaining(String name, String position);
}