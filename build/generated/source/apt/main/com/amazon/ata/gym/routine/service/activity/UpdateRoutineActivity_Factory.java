package com.amazon.ata.gym.routine.service.activity;

import com.amazon.ata.gym.routine.service.dynamodb.RoutineDao;
import dagger.internal.Factory;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class UpdateRoutineActivity_Factory implements Factory<UpdateRoutineActivity> {
  private final Provider<RoutineDao> routineDaoProvider;

  public UpdateRoutineActivity_Factory(Provider<RoutineDao> routineDaoProvider) {
    this.routineDaoProvider = routineDaoProvider;
  }

  @Override
  public UpdateRoutineActivity get() {
    return new UpdateRoutineActivity(routineDaoProvider.get());
  }

  public static UpdateRoutineActivity_Factory create(Provider<RoutineDao> routineDaoProvider) {
    return new UpdateRoutineActivity_Factory(routineDaoProvider);
  }
}
