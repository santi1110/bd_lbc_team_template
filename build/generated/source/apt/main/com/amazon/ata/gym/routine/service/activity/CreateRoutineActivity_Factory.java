package com.amazon.ata.gym.routine.service.activity;

import com.amazon.ata.gym.routine.service.dynamodb.RoutineDao;
import dagger.internal.Factory;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class CreateRoutineActivity_Factory implements Factory<CreateRoutineActivity> {
  private final Provider<RoutineDao> routineDaoProvider;

  public CreateRoutineActivity_Factory(Provider<RoutineDao> routineDaoProvider) {
    this.routineDaoProvider = routineDaoProvider;
  }

  @Override
  public CreateRoutineActivity get() {
    return new CreateRoutineActivity(routineDaoProvider.get());
  }

  public static CreateRoutineActivity_Factory create(Provider<RoutineDao> routineDaoProvider) {
    return new CreateRoutineActivity_Factory(routineDaoProvider);
  }
}
