package com.example.attendancesystem.models;

import java.time.LocalTime;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
public class WorkedHours {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Temporal(value = TemporalType.DATE)
  private Date date;
  private int month;
  private LocalTime start;
  private LocalTime end;
  private Double hoursWorked;

  @ManyToOne
  @JoinColumn
  private Employee employee;

  public WorkedHours(Date date, LocalTime start, Employee employee) {
    this.date = date;
    this.start = start;
    this.employee = employee;
    this.month = date.getMonth() + 1;
  }

  public void setHoursWorked(Double hoursWorked) {
    this.hoursWorked = Math.round(hoursWorked / 60 * 2) / 2.0;
  }
}