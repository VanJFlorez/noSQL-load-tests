package com.clarocol.mongoLoadTest.service;

import java.nio.charset.Charset;
import java.util.Optional;
import java.util.Random;

import com.clarocol.mongoLoadTest.dto.TestResults;
import com.clarocol.mongoLoadTest.entity.RandomDocument;
import com.clarocol.mongoLoadTest.repository.LoadTestRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoadTestService {

  @Autowired
  LoadTestRepo loadTestRepo;

  public TestResults create(long docsQtty, int fieldQtty, boolean sameDocs) {
    int minStrLength = 100;
    int maxStrLength = 200;
    long collectionCount = loadTestRepo.count();
    long j = 0;
    long startTime = System.nanoTime();
    RandomDocument doc = new RandomDocument(fieldQtty, minStrLength, maxStrLength);
    for (j = 0; j < docsQtty; j++) {
      if (sameDocs)
        loadTestRepo.save(doc);
      else
        loadTestRepo.save(new RandomDocument(fieldQtty, minStrLength, maxStrLength));
    }
    long endTime = System.nanoTime();
    double elapsed = (endTime - startTime) / 1_000_000.0;

    return new TestResults(elapsed, j, collectionCount, "CREATE", "Nice.");
  }

  public TestResults read(double proportion) {
    long collectionCount = loadTestRepo.count();
    long queriesQtty = (long) (collectionCount * proportion);
    long startTime = System.nanoTime();
    long i = 0;
    for (i = 0; i < queriesQtty; i++) {
      RandomDocument doc = loadTestRepo.randomSample();
    }
    long endTime = System.nanoTime();
    double elapsed = (endTime - startTime)/1_000_000.0;
    return new TestResults(elapsed, i, collectionCount, "READ", "Nice.");
  }

  public TestResults update(double proportion) {
    long collectionCount = loadTestRepo.count();
    long queriesQtty = (long) (collectionCount * proportion);
    long startTime = System.nanoTime();
    long i = 0;

    long endTime = System.nanoTime();
    double elapsed = (endTime - startTime)/1_000_000.0;
    return null;
  }
  
  public TestResults delete() {
    return null;
  }
}