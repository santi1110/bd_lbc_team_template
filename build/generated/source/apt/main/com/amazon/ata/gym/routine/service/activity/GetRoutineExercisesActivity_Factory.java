package com.amazon.ata.gym.routine.service.activity;

import com.amazon.ata.gym.routine.service.dao.RoutineDao;
import dagger.internal.Factory;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class GetRoutineExercisesActivity_Factory
    implements Factory<GetRoutineExercisesActivity> {
  private final Provider<RoutineDao> routineDaoProvider;

  public GetRoutineExercisesActivity_Factory(Provider<RoutineDao> routineDaoProvider) {
    this.routineDaoProvider = routineDaoProvider;
  }

  @Override
  public GetRoutineExercisesActivity get() {
    return new GetRoutineExercisesActivity(routineDaoProvider.get());
  }

  public static GetRoutineExercisesActivity_Factory create(
      Provider<RoutineDao> routineDaoProvider) {
    return new GetRoutineExercisesActivity_Factory(routineDaoProvider);
  }
}
