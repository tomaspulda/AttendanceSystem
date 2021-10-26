package com.example.attendancesystem.repositories;

import com.example.attendancesystem.models.Employee;
import com.example.attendancesystem.models.WorkedHours;
import java.time.Month;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkedHoursRepository extends JpaRepository<WorkedHours, Long> {

  List<WorkedHours> getByEmployeeOrderByIdDesc(Employee employee);

  List<WorkedHours> getByEmployeeOrderByDateDesc(Employee employee);

  List<WorkedHours> findAllByEmployeeAndMonth(Employee employee, String month);

  List<WorkedHours> findAllByEmployee(Employee employee);

}