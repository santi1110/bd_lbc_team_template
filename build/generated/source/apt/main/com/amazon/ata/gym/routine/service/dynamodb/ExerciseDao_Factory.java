package com.amazon.ata.gym.routine.service.dynamodb;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import dagger.internal.Factory;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class ExerciseDao_Factory implements Factory<ExerciseDao> {
  private final Provider<DynamoDBMapper> dynamoDbMapperProvider;

  public ExerciseDao_Factory(Provider<DynamoDBMapper> dynamoDbMapperProvider) {
    this.dynamoDbMapperProvider = dynamoDbMapperProvider;
  }

  @Override
  public ExerciseDao get() {
    return new ExerciseDao(dynamoDbMapperProvider.get());
  }

  public static ExerciseDao_Factory create(Provider<DynamoDBMapper> dynamoDbMapperProvider) {
    return new ExerciseDao_Factory(dynamoDbMapperProvider);
  }
}
