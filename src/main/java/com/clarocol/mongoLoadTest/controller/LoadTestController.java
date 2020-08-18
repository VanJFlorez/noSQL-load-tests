package com.clarocol.mongoLoadTest.controller;

import com.clarocol.mongoLoadTest.dto.TestResults;
import com.clarocol.mongoLoadTest.service.LoadTestService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
public class LoadTestController {
  private static final String PROPORTION_DOC = "The proportion of documents to impact. Is a number between 0 and 1.";

  @Autowired
  LoadTestService loadTestService;

  @GetMapping("/create")
  @ApiOperation(
    value ="Stores random documents in the database", 
    notes ="Returns the spent time in seconds and the overall document count", 
    response = TestResults.class)
	@ApiResponses( 
    value = @ApiResponse(code = 200, response = TestResults.class, message = "OK"))
  public TestResults create(@RequestParam Long repeat, @RequestParam Integer fields) {        
    return loadTestService.create(repeat, fields, false);
  }
  
  @GetMapping("/update")
  @ApiOperation(
    value ="Updates the given **proportion** of total documents by replace", 
    notes ="The update operation is performed by overwritting the entire document. " + 
            "Returns the spent time in seconds and the overall document count", 
    response = TestResults.class)
  public TestResults update(@ApiParam(value = PROPORTION_DOC, 
                                      required = true, 
                                      type ="decimal" ) @RequestParam String proportion) {
    double prop = Double.parseDouble(proportion);
    return loadTestService.replace(prop);
  }
  

  @ApiOperation(
    value ="Reads randomly and one by one the given **proportion** of total documents.", 
    response = TestResults.class)
  @GetMapping("/read")
  public TestResults read(@ApiParam(value = PROPORTION_DOC) @RequestParam String proportion) {
    double prop = Double.parseDouble(proportion);
    return loadTestService.read(prop);
  }

  @GetMapping("/delete")
  public TestResults delete(@ApiParam(value = PROPORTION_DOC) @RequestParam String proportion) {
    double prop = Double.parseDouble(proportion);
    return loadTestService.delete(prop);
  }
  
}