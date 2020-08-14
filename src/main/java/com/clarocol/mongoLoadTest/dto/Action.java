package com.clarocol.mongoLoadTest.dto;

public enum Action {
  CREATE("CREATE"),
  READ("READ"),
  UPDATE("UPDATE"),
  DELETE("DELETE");

  public String name;

  private Action(String name) { this.name = name; }
}