package com.amazon.ata.gym.routine.service.lambda;

import com.amazon.ata.gym.routine.service.activity.GetRoutineExercisesActivity;
import com.amazon.ata.gym.routine.service.dependency.DaggerServiceComponent;
import com.amazon.ata.gym.routine.service.dependency.ServiceComponent;
import com.amazon.ata.gym.routine.service.models.requests.GetRoutineExercisesRequest;
import com.amazon.ata.gym.routine.service.models.results.GetRoutineExercisesResult;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class GetRoutineExercisesActivityProvider implements RequestHandler<GetRoutineExercisesRequest, GetRoutineExercisesResult> {

    private static ServiceComponent serviceComponent;

    private GetRoutineExercisesActivity getRoutineExercisesActivity;

    public GetRoutineExercisesActivityProvider() {
        if (serviceComponent == null) {
            serviceComponent = DaggerServiceComponent.create();
        }
        getRoutineExercisesActivity = serviceComponent.provideGetRoutineExercisesActivity();
    }

    @Override
    public GetRoutineExercisesResult handleRequest(final GetRoutineExercisesRequest getRoutineExercisesRequest, Context context) {
        return getRoutineExercisesActivity.handleRequest(getRoutineExercisesRequest, context);
    }
}


