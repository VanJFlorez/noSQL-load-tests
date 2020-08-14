package com.clarocol.mongoLoadTest.repository;

import com.clarocol.mongoLoadTest.entity.RandomDocument;

import org.springframework.data.mongodb.repository.MongoRepository;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.sample;

public interface LoadTestRepo extends MongoRepository<RandomDocument, Long>, RandomSamplesRepo {
  // @Query(value="{ calleeNo: { $eq: ?0 } }, { callerNo: 1, url: 1, preventingTime: 1, _id: 0 }")
  // List<RandomDocument> getPreventedURLsFromCalleeNumber(); 
  
  public long count();
}