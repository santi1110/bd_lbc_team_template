package com.amazon.ata.gym.routine.service.lambda;

import com.amazon.ata.gym.routine.service.activity.AddExerciseToRoutineActivity;
import com.amazon.ata.gym.routine.service.dependency.DaggerServiceComponent;
import com.amazon.ata.gym.routine.service.dependency.ServiceComponent;
import com.amazon.ata.gym.routine.service.models.requests.AddExerciseToRoutineRequest;
import com.amazon.ata.gym.routine.service.models.results.AddExerciseToRoutineResult;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class AddExerciseToRoutineActivityProvider implements RequestHandler<AddExerciseToRoutineRequest, AddExerciseToRoutineResult> {

    private static ServiceComponent serviceComponent;

    private AddExerciseToRoutineActivity addExerciseToRoutineActivity;

    public AddExerciseToRoutineActivityProvider() {
        if (serviceComponent == null) {
            serviceComponent = DaggerServiceComponent.create();
        }
        addExerciseToRoutineActivity = serviceComponent.provideAddExerciseToRoutineActivity();
    }

    @Override
    public AddExerciseToRoutineResult handleRequest(final AddExerciseToRoutineRequest addExerciseToRoutineRequest, Context context) {
        return addExerciseToRoutineActivity.handleRequest(addExerciseToRoutineRequest, context);
    }
}


