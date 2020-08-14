package com.clarocol.mongoLoadTest.repository;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.sample;

import java.util.List;

import com.clarocol.mongoLoadTest.entity.RandomDocument;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.stereotype.Repository;

@Repository
public class RandomSamplesRepoImpl implements RandomSamplesRepo {

  @Autowired
  MongoTemplate mongoTemplate;

  @Override
  public RandomDocument randomSample() {
    Aggregation agg = newAggregation(
      sample(1)
    );
    
    AggregationResults<RandomDocument> results = mongoTemplate.aggregate(agg, RandomDocument.class, RandomDocument.class);
    List<RandomDocument> randomDocument = results.getMappedResults();

    if (randomDocument.size() != 0) {
      return randomDocument.get(0);
    } else {
      return null;
    }
  }

  
}