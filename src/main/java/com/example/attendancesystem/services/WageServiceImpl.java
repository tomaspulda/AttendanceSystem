package com.example.attendancesystem.services;

import com.example.attendancesystem.models.Employee;
import com.example.attendancesystem.models.Wage;
import com.example.attendancesystem.repositories.WageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WageServiceImpl implements WageService{

  private WorkedHoursService workedHoursService;
  private WageRepository wageRepository;

  @Autowired
  public WageServiceImpl(WorkedHoursService workedHoursService,
      WageRepository wageRepository) {
    this.workedHoursService = workedHoursService;
    this.wageRepository = wageRepository;
  }

  @Override
  public void calculateWage(Employee employee, int month, int bonus) {
    int sumOfHours = workedHoursService.getMonthByEmployee(employee, month);
    int hourlyWage = employee.getHourlyWage();
    Wage wage = createWage(employee, month, bonus);

    int grossWage = sumOfHours*hourlyWage;
    wage.setGrossWage(grossWage);

    wage.setBonus(bonus);

    int incomeTax = (int) Math.ceil((bonus + grossWage) * 0.15) - taxCredit();

    int socIns = (int) Math.ceil((grossWage+bonus)*0.065);
    wage.setSocialInsuranceEmplee(socIns);
    int healthIns = (int) Math.ceil((grossWage+bonus)*0.045);
    wage.setHealthInsuranceEmplee(healthIns);

    int socInsEmplr = (int) Math.ceil((bonus + grossWage) * 0.25);
    wage.setSocialInsuranceEmplr(socInsEmplr);
    int healtInsEmplr = (int) Math.ceil((bonus + grossWage) * 0.09);
    wage.setHealthInsuranceEmplr(healtInsEmplr);

    wage.setNetWage(wage.getGrossWage() - incomeTax - socIns - healthIns);
  }

  @Override
  public int taxCredit() {
    return 2320;
  }

  @Override
  public Wage createWage(Employee employee, int month, int bonus) {
    return wageRepository.save(new Wage(month, employee, bonus));
  }
}
