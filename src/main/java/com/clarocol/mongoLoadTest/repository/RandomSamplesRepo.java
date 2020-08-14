package com.clarocol.mongoLoadTest.repository;

import com.clarocol.mongoLoadTest.entity.RandomDocument;

public interface RandomSamplesRepo {
  RandomDocument randomSample();
}