package com.amazon.ata.gym.routine.service.dependency;

import com.amazon.ata.gym.routine.service.dao.ExerciseDao;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class DaoModule_ProvideExerciseDaoFactory implements Factory<ExerciseDao> {
  private final DaoModule module;

  private final Provider<DynamoDBMapper> dynamoDbMapperProvider;

  public DaoModule_ProvideExerciseDaoFactory(
      DaoModule module, Provider<DynamoDBMapper> dynamoDbMapperProvider) {
    this.module = module;
    this.dynamoDbMapperProvider = dynamoDbMapperProvider;
  }

  @Override
  public ExerciseDao get() {
    return Preconditions.checkNotNull(
        module.provideExerciseDao(dynamoDbMapperProvider.get()),
        "Cannot return null from a non-@Nullable @Provides method");
  }

  public static DaoModule_ProvideExerciseDaoFactory create(
      DaoModule module, Provider<DynamoDBMapper> dynamoDbMapperProvider) {
    return new DaoModule_ProvideExerciseDaoFactory(module, dynamoDbMapperProvider);
  }

  public static ExerciseDao proxyProvideExerciseDao(
      DaoModule instance, DynamoDBMapper dynamoDbMapper) {
    return Preconditions.checkNotNull(
        instance.provideExerciseDao(dynamoDbMapper),
        "Cannot return null from a non-@Nullable @Provides method");
  }
}
