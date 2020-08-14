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

  @GetMapping("/loadTest")
  public TestResults launchTest(@RequestParam String operation, @RequestParam(required = false) String proportion,  @RequestParam Long repeat, @RequestParam Integer fields) {        
    switch (operation) {
      case "CREATE":
        return loadTestService.create(repeat, fields, false);
      case "READ":
        return loadTestService.read(0.0);
      case "UPDATE":
        return loadTestService.update(0.0);
      case "DELETE":
        return loadTestService.delete();
      default:
        return new TestResults(0, 0L, 0L, "?", "Operations available 'CREATE', 'READ', 'UPDATE', 'DELETE'");
    }
  }

  @GetMapping("/read")
  public TestResults read(@RequestParam String proportion) {
    double prop = Double.parseDouble(proportion);
    return loadTestService.read(prop);
  }
}