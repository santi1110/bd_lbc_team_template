package com.amazon.ata.gym.routine.service.dao;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import dagger.internal.Factory;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class RoutineDao_Factory implements Factory<RoutineDao> {
  private final Provider<DynamoDBMapper> dynamoDbMapperProvider;

  public RoutineDao_Factory(Provider<DynamoDBMapper> dynamoDbMapperProvider) {
    this.dynamoDbMapperProvider = dynamoDbMapperProvider;
  }

  @Override
  public RoutineDao get() {
    return new RoutineDao(dynamoDbMapperProvider.get());
  }

  public static RoutineDao_Factory create(Provider<DynamoDBMapper> dynamoDbMapperProvider) {
    return new RoutineDao_Factory(dynamoDbMapperProvider);
  }
}
