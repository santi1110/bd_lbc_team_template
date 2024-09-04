package com.amazon.ata.gym.routine.service.activity;

import com.amazon.ata.gym.routine.service.dynamodb.RoutineDao;
import dagger.internal.Factory;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class GetRoutineActivity_Factory implements Factory<GetRoutineActivity> {
  private final Provider<RoutineDao> routineDaoProvider;

  public GetRoutineActivity_Factory(Provider<RoutineDao> routineDaoProvider) {
    this.routineDaoProvider = routineDaoProvider;
  }

  @Override
  public GetRoutineActivity get() {
    return new GetRoutineActivity(routineDaoProvider.get());
  }

  public static GetRoutineActivity_Factory create(Provider<RoutineDao> routineDaoProvider) {
    return new GetRoutineActivity_Factory(routineDaoProvider);
  }
}
