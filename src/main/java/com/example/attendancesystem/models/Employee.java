package com.example.attendancesystem.models;

import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
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
  @Temporal(value = TemporalType.DATE)
  private Date dayOfBirth;
  private String position;
  @Temporal(value = TemporalType.DATE)
  private Date dateOfStart;
  private boolean atWork;

  @OneToOne(mappedBy = "employee")
  private Contact contact;

  @OneToMany(mappedBy = "employee")
  private List<WorkedHours> workedHoursList;


  public Employee(String name, String position) {
    this.name = name;
    this.position = position;
    this.atWork = false;
  }
}
