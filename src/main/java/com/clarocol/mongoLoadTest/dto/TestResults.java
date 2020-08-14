package com.clarocol.mongoLoadTest.dto;

public class TestResults {

  public Double diffMilis;
  public Long operations;
  public Long collectionCount;
  public String operation;
  public String feedback;

  public TestResults(double diffMilis, Long documentQtty, long collectionCount, String operation, String feedback) {
    this.diffMilis = diffMilis;
    this.operations = documentQtty;
    this.collectionCount = collectionCount;
    this.operation = operation;
    this.feedback = feedback;
  }
}