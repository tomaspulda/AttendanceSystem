package com.example.attendancesystem.repositories;

import com.example.attendancesystem.models.Wage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WageRepository extends JpaRepository<Wage, Long> {

}
