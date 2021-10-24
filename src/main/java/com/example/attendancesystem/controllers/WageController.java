package com.example.attendancesystem.controllers;

import com.example.attendancesystem.services.WageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/wage")
public class WageController {

  private WageService wageService;

  @Autowired
  public WageController(WageService wageService) {
    this.wageService = wageService;
  }

  @GetMapping("/")
  public String getWageCalculator() {
    return "wagecalculator";
  }
}
