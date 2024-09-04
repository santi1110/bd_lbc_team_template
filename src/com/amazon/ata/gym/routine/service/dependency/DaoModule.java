package com.amazon.ata.gym.routine.service.dependency;

import com.amazon.ata.gym.routine.service.dao.ExerciseDao; // Updated DAO import
import com.amazon.ata.gym.routine.service.dao.RoutineDao;   // Updated DAO import
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

@Module
public class DaoModule {
    @Provides
    @Singleton
    public AmazonDynamoDB provideAmazonDynamoDB() {
        return AmazonDynamoDBClientBuilder.defaultClient();
    }

    @Provides
    @Singleton
    public DynamoDBMapper provideDynamoDBMapper(AmazonDynamoDB amazonDynamoDB) {
        return new DynamoDBMapper(amazonDynamoDB);
    }

    @Provides
    @Singleton
    public ExerciseDao provideExerciseDao(DynamoDBMapper dynamoDbMapper) {
        return new ExerciseDao(dynamoDbMapper); // Updated to ExerciseDao
    }

    @Provides
    @Singleton
    public RoutineDao provideRoutineDao(DynamoDBMapper dynamoDbMapper) {
        return new RoutineDao(dynamoDbMapper); // Updated to RoutineDao
    }
}
