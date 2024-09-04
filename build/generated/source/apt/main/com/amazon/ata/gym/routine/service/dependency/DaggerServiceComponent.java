package com.amazon.ata.gym.routine.service.dependency;

import com.amazon.ata.gym.routine.service.activity.AddExerciseToRoutineActivity;
import com.amazon.ata.gym.routine.service.activity.CreateRoutineActivity;
import com.amazon.ata.gym.routine.service.activity.GetRoutineActivity;
import com.amazon.ata.gym.routine.service.activity.GetRoutineExercisesActivity;
import com.amazon.ata.gym.routine.service.activity.UpdateRoutineActivity;
import com.amazon.ata.gym.routine.service.dynamodb.ExerciseDao;
import com.amazon.ata.gym.routine.service.dynamodb.RoutineDao;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import dagger.internal.DoubleCheck;
import dagger.internal.Preconditions;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class DaggerServiceComponent implements ServiceComponent {
  private Provider<AmazonDynamoDB> provideAmazonDynamoDBProvider;

  private Provider<DynamoDBMapper> provideDynamoDBMapperProvider;

  private Provider<RoutineDao> provideRoutineDaoProvider;

  private Provider<ExerciseDao> provideExerciseDaoProvider;

  private DaggerServiceComponent(Builder builder) {
    initialize(builder);
  }

  public static Builder builder() {
    return new Builder();
  }

  public static ServiceComponent create() {
    return new Builder().build();
  }

  @SuppressWarnings("unchecked")
  private void initialize(final Builder builder) {
    this.provideAmazonDynamoDBProvider =
        DoubleCheck.provider(DaoModule_ProvideAmazonDynamoDBFactory.create(builder.daoModule));
    this.provideDynamoDBMapperProvider =
        DoubleCheck.provider(
            DaoModule_ProvideDynamoDBMapperFactory.create(
                builder.daoModule, provideAmazonDynamoDBProvider));
    this.provideRoutineDaoProvider =
        DoubleCheck.provider(
            DaoModule_ProvideRoutineDaoFactory.create(
                builder.daoModule, provideDynamoDBMapperProvider));
    this.provideExerciseDaoProvider =
        DoubleCheck.provider(
            DaoModule_ProvideExerciseDaoFactory.create(
                builder.daoModule, provideDynamoDBMapperProvider));
  }

  @Override
  public AddExerciseToRoutineActivity provideAddExerciseToRoutineActivity() {
    return new AddExerciseToRoutineActivity(
        provideRoutineDaoProvider.get(), provideExerciseDaoProvider.get());
  }

  @Override
  public CreateRoutineActivity provideCreateRoutineActivity() {
    return new CreateRoutineActivity(provideRoutineDaoProvider.get());
  }

  @Override
  public GetRoutineActivity provideGetRoutineActivity() {
    return new GetRoutineActivity(provideRoutineDaoProvider.get());
  }

  @Override
  public GetRoutineExercisesActivity provideGetRoutineExercisesActivity() {
    return new GetRoutineExercisesActivity(provideRoutineDaoProvider.get());
  }

  @Override
  public UpdateRoutineActivity provideUpdateRoutineActivity() {
    return new UpdateRoutineActivity(provideRoutineDaoProvider.get());
  }

  public static final class Builder {
    private DaoModule daoModule;

    private Builder() {}

    public ServiceComponent build() {
      if (daoModule == null) {
        this.daoModule = new DaoModule();
      }
      return new DaggerServiceComponent(this);
    }

    public Builder daoModule(DaoModule daoModule) {
      this.daoModule = Preconditions.checkNotNull(daoModule);
      return this;
    }
  }
}
