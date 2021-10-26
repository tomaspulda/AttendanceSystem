package com.example.attendancesystem.services;

import com.example.attendancesystem.models.Employee;
import com.example.attendancesystem.models.Wage;
import com.example.attendancesystem.repositories.WageRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WageServiceImpl implements WageService {

  private WorkedHoursService workedHoursService;
  private WageRepository wageRepository;

  @Autowired
  public WageServiceImpl(WorkedHoursService workedHoursService,
      WageRepository wageRepository) {
    this.workedHoursService = workedHoursService;
    this.wageRepository = wageRepository;
  }

  @Override
  public void createWage(int bonus, String month, int kids, boolean spouse, boolean handicappedI,
      boolean handicappedII_III, boolean physicalDisability, boolean student,
      Employee employee) {
    List<Wage> wageList = wageRepository
        .findAllByEmployee_IdAndMonthOrderByIdDesc(employee.getId(), month);
    for (Wage w : wageList) {
      wageRepository.delete(w);
    }
    Wage wage = new Wage(bonus, month, kids, spouse, handicappedI, handicappedII_III, physicalDisability, student, employee);
    calculateWage(wage);
  }

  @Override
  public void calculateWage(Wage wage) {
    int sumOfHours = workedHoursService.getMonthByEmployee(wage.getEmployee(), wage.getMonth());
    int hourlyWage = wage.getEmployee().getHourlyWage();

    int grossWage = sumOfHours * hourlyWage;
    wage.setGrossWage(grossWage);

    int socIns = (int) Math.ceil((grossWage + wage.getBonus()) * 0.065);
    wage.setSocialInsuranceEmplee(socIns);
    int healthIns = (int) Math.ceil((grossWage + wage.getBonus()) * 0.045);
    wage.setHealthInsuranceEmplee(healthIns);

    int socInsEmplr = (int) Math.ceil((wage.getBonus() + grossWage) * 0.248);
    wage.setSocialInsuranceEmplr(socInsEmplr);
    int healthInsEmplr = (int) Math.ceil((wage.getBonus() + grossWage) * 0.09);
    wage.setHealthInsuranceEmplr(healthInsEmplr);

    wage.setIncomeTax(calculateTax(wage));
    wage.setNetWage(wage.getGrossWage() + wage.getBonus() - socIns - healthIns - calculateTax(wage));
    wageRepository.save(wage);
  }

  @Override
  public int calculateTax(Wage wage) {
    int incomeTax = (int) Math.ceil((wage.getBonus() + wage.getGrossWage()) * 0.15);

    incomeTax -= 2320;

    if (wage.isSpouse()) {
      incomeTax -= 2070;
    }
    if (wage.isHandicappedI()) {
      incomeTax -= 210;
    }
    if (wage.isHandicappedII_III()) {
      incomeTax -= 420;
    }
    if (wage.isPhysicalDisability()) {
      incomeTax -= 1345;
    }
    if (wage.isStudent()) {
      incomeTax -= 335;
    }
    if (incomeTax<0) {
      incomeTax = 0;
    }
    if (wage.getKids() == 1) {
      incomeTax -= 1267;
    }
    if (wage.getKids() == 2) {
      incomeTax -= 2884;
    }

    if (wage.getKids() >= 3) {
      int discount = 2884 + ((wage.getKids()-2)*2017);
      incomeTax -= discount;
    }
    return incomeTax;
  }

  @Override
  public Wage getWage(Employee employee, String month) {
    return wageRepository.findAllByEmployee_IdAndMonthOrderByIdDesc(employee.getId(), month).get(0);
  }
}
