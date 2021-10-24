package com.example.attendancesystem.models;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Employee {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;
  private String dayOfBirth;
  private String position;
  private String dateOfStart;
  private boolean atWork;
  private int hourlyWage;

  @OneToOne(mappedBy = "employee")
  private Contact contact;

  @OneToOne(mappedBy = "employee")
  private Wage wage;

  @OneToMany(mappedBy = "employee")
  private List<WorkedHours> workedHoursList;

  public Employee(String name, String position) {
    this.name = name;
    this.position = position;
    this.atWork = false;
  }

  public Employee(String name, String dayOfBirth, String position, String dateOfStart,
      int hourlyWage) {
    this.name = name;
    this.dayOfBirth = dayOfBirth;
    this.position = position;
    this.dateOfStart = dateOfStart;
    this.hourlyWage = hourlyWage;
    this.atWork = false;
  }
}