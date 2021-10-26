package com.example.attendancesystem.repositories;

import com.example.attendancesystem.models.Wage;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WageRepository extends JpaRepository<Wage, Long> {

  List<Wage> findAllByEmployee_IdAndMonth(Long id, String month);
}
