package com.example.attendancesystem.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
public class Contact {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String street;
  private int houseNumber;
  private String city;
  private int postCode;
  private String country;
  private Long phoneNumber;

  @OneToOne
  private Employee employee;

  public Contact(String street, int houseNumber, String city, int postCode, String country,
      Long phoneNumber, Employee employee) {
    this.street = street;
    this.houseNumber = houseNumber;
    this.city = city;
    this.postCode = postCode;
    this.country = country;
    this.phoneNumber = phoneNumber;
    this.employee = employee;
  }
}
