package com.clarocol.mongoLoadTest.controller;

import com.clarocol.mongoLoadTest.dto.TestResults;
import com.clarocol.mongoLoadTest.service.LoadTestService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoadTestController {

  @Autowired
  LoadTestService loadTestService;

  @GetMapping("/create")
  public TestResults create(@RequestParam Long repeat, @RequestParam Integer fields) {        
    return loadTestService.create(repeat, fields, false);
  }

  @GetMapping("/read")
  public TestResults read(@RequestParam String proportion) {
    double prop = Double.parseDouble(proportion);
    return loadTestService.read(prop);
  }
  
  @GetMapping("/update")
  public TestResults update(@RequestParam String proportion) {
    double prop = Double.parseDouble(proportion);
    return loadTestService.replace(prop);
  }
  
  @GetMapping("/delete")
  public TestResults delete(@RequestParam String proportion) {
    double prop = Double.parseDouble(proportion);
    return loadTestService.delete(prop);
  }
  
}