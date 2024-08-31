package com.amazon.ata.gym.routine.service.lambda;

import com.amazon.ata.gym.routine.service.activity.UpdateRoutineActivity;
import com.amazon.ata.gym.routine.service.dependency.DaggerServiceComponent;
import com.amazon.ata.gym.routine.service.dependency.ServiceComponent;
import com.amazon.ata.gym.routine.service.models.requests.UpdateRoutineRequest;
import com.amazon.ata.gym.routine.service.models.results.UpdateRoutineResult;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class UpdateRoutineActivityProvider implements RequestHandler<UpdateRoutineRequest, UpdateRoutineResult> {

    private static ServiceComponent serviceComponent;

    private UpdateRoutineActivity updateRoutineActivity;

    public UpdateRoutineActivityProvider() {
        if (serviceComponent == null) {
            serviceComponent = DaggerServiceComponent.create();
        }
        updateRoutineActivity = serviceComponent.provideUpdateRoutineActivity();
    }

    @Override
    public UpdateRoutineResult handleRequest(final UpdateRoutineRequest updateRoutineRequest, Context context) {
        return updateRoutineActivity.handleRequest(updateRoutineRequest, context);
    }
}

