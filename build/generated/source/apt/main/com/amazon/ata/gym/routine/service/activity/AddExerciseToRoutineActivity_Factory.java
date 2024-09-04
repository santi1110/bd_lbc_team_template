package com.amazon.ata.gym.routine.service.activity;

import com.amazon.ata.gym.routine.service.dao.ExerciseDao;
import com.amazon.ata.gym.routine.service.dao.RoutineDao;
import dagger.internal.Factory;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class AddExerciseToRoutineActivity_Factory
    implements Factory<AddExerciseToRoutineActivity> {
  private final Provider<RoutineDao> routineDaoProvider;

  private final Provider<ExerciseDao> exerciseDaoProvider;

  public AddExerciseToRoutineActivity_Factory(
      Provider<RoutineDao> routineDaoProvider, Provider<ExerciseDao> exerciseDaoProvider) {
    this.routineDaoProvider = routineDaoProvider;
    this.exerciseDaoProvider = exerciseDaoProvider;
  }

  @Override
  public AddExerciseToRoutineActivity get() {
    return new AddExerciseToRoutineActivity(routineDaoProvider.get(), exerciseDaoProvider.get());
  }

  public static AddExerciseToRoutineActivity_Factory create(
      Provider<RoutineDao> routineDaoProvider, Provider<ExerciseDao> exerciseDaoProvider) {
    return new AddExerciseToRoutineActivity_Factory(routineDaoProvider, exerciseDaoProvider);
  }
}
