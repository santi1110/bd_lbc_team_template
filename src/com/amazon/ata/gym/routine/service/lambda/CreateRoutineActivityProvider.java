package com.amazon.ata.gym.routine.service.lambda;

import com.amazon.ata.gym.routine.service.activity.CreateRoutineActivity;
import com.amazon.ata.gym.routine.service.dependency.DaggerServiceComponent;
import com.amazon.ata.gym.routine.service.dependency.ServiceComponent;
import com.amazon.ata.gym.routine.service.models.requests.CreateRoutineRequest;
import com.amazon.ata.gym.routine.service.models.results.CreateRoutineResult;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class CreateRoutineActivityProvider implements RequestHandler<CreateRoutineRequest, CreateRoutineResult> {

    private static ServiceComponent serviceComponent;

    private CreateRoutineActivity createRoutineActivity;

    public CreateRoutineActivityProvider() {
        if (serviceComponent == null) {
            serviceComponent = DaggerServiceComponent.create();
        }
        createRoutineActivity = serviceComponent.provideCreateRoutineActivity();
    }

    @Override
    public CreateRoutineResult handleRequest(final CreateRoutineRequest createRoutineRequest, Context context) {
        return createRoutineActivity.handleRequest(createRoutineRequest, context);
    }
}

