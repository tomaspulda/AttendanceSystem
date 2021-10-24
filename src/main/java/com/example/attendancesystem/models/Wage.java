package com.example.attendancesystem.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Wage {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private int grossWage;
  private int bonus;
  private int healthInsuranceEmplr;
  private int socialInsuranceEmplr;
  private int healthInsuranceEmplee;
  private int socialInsuranceEmplee;
  private int incomeTax;
  private int netWage;
  private int month;

  @OneToOne
  private Employee employee;

  public Wage(int month, Employee employee, int bonus) {
    this.month = month;
    this.employee = employee;
    this.bonus = bonus;
  }
}
