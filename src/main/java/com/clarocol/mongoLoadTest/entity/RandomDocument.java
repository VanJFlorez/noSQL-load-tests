package com.clarocol.mongoLoadTest.entity;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Random;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class RandomDocument extends HashMap<String, String> {

  private static final long serialVersionUID = -4166329813753130505L;

  public RandomDocument() {}

  public RandomDocument(int fieldQtty, int minStrLength, int maxStrLength) {
    for(int i = 0; i < fieldQtty; i++) {
      put("randomField" + i, generateRandomString(minStrLength, maxStrLength));
    }
  }

  private static String generateRandomString(int minLength, int maxLength) {
    if (maxLength < minLength)
      return new String();
    
    Random rnd = new Random();
    byte[] array = new byte[rnd.nextInt(maxLength - minLength + 1) + minLength];
    rnd.nextBytes(array);
    return new String(array, Charset.forName("UTF-8"));
  }
}