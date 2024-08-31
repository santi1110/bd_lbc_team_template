package com.amazon.ata.gym.routine.service.lambda;

import com.amazon.ata.gym.routine.service.activity.GetRoutineActivity;
import com.amazon.ata.gym.routine.service.dependency.DaggerServiceComponent;
import com.amazon.ata.gym.routine.service.dependency.ServiceComponent;
import com.amazon.ata.gym.routine.service.models.requests.GetRoutineRequest;
import com.amazon.ata.gym.routine.service.models.results.GetRoutineResult;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class GetRoutineActivityProvider implements RequestHandler<GetRoutineRequest, GetRoutineResult> {

    private static ServiceComponent serviceComponent;

    private GetRoutineActivity getRoutineActivity;

    public GetRoutineActivityProvider() {
        if (serviceComponent == null) {
            serviceComponent = DaggerServiceComponent.create();
        }
        getRoutineActivity = serviceComponent.provideGetRoutineActivity();
    }

    @Override
    public GetRoutineResult handleRequest(final GetRoutineRequest getRoutineRequest, Context context) {
        return getRoutineActivity.handleRequest(getRoutineRequest, context);
    }
}

