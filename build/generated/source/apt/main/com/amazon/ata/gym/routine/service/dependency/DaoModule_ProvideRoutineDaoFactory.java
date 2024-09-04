package com.amazon.ata.gym.routine.service.dependency;

import com.amazon.ata.gym.routine.service.dao.RoutineDao;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class DaoModule_ProvideRoutineDaoFactory implements Factory<RoutineDao> {
  private final DaoModule module;

  private final Provider<DynamoDBMapper> dynamoDbMapperProvider;

  public DaoModule_ProvideRoutineDaoFactory(
      DaoModule module, Provider<DynamoDBMapper> dynamoDbMapperProvider) {
    this.module = module;
    this.dynamoDbMapperProvider = dynamoDbMapperProvider;
  }

  @Override
  public RoutineDao get() {
    return Preconditions.checkNotNull(
        module.provideRoutineDao(dynamoDbMapperProvider.get()),
        "Cannot return null from a non-@Nullable @Provides method");
  }

  public static DaoModule_ProvideRoutineDaoFactory create(
      DaoModule module, Provider<DynamoDBMapper> dynamoDbMapperProvider) {
    return new DaoModule_ProvideRoutineDaoFactory(module, dynamoDbMapperProvider);
  }

  public static RoutineDao proxyProvideRoutineDao(
      DaoModule instance, DynamoDBMapper dynamoDbMapper) {
    return Preconditions.checkNotNull(
        instance.provideRoutineDao(dynamoDbMapper),
        "Cannot return null from a non-@Nullable @Provides method");
  }
}
