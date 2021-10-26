package com.example.attendancesystem.repositories;

import com.example.attendancesystem.models.Employee;
import com.example.attendancesystem.models.Wage;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WageRepository extends JpaRepository<Wage, Long> {

  List<Wage> findAllByEmployee_IdAndMonthOrderByIdDesc(Long id, String month);

  List<Wage> findAllByEmployee_Id(Long id);
}
