package com.amazon.ata.gym.routine.service.dependency;

import com.amazon.ata.gym.routine.service.activity.*;
import com.amazon.ata.gym.routine.service.lambda.*;
import dagger.Component;

import javax.inject.Singleton;

@Singleton
@Component(modules = {DaoModule.class})
public interface ServiceComponent {
/*
    void inject(AddExerciseToRoutineActivityProvider addExerciseToRoutineActivityProvider);
    void inject(CreateRoutineActivityProvider createRoutineActivityProvider);
    void inject(GetRoutineActivityProvider getRoutineActivityProvider);
    void inject(GetRoutineExercisesActivityProvider getExercisesFromRoutineActivityProvider);
    void inject(UpdateRoutineActivityProvider updateRoutineActivityProvider);
*/

    AddExerciseToRoutineActivity provideAddExerciseToRoutineActivity();
    CreateRoutineActivity provideCreateRoutineActivity();
    GetRoutineActivity provideGetRoutineActivity();
    GetRoutineExercisesActivity provideGetRoutineExercisesActivity();
    UpdateRoutineActivity provideUpdateRoutineActivity();
}
