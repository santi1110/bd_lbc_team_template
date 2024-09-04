package com.amazon.ata.gym.routine.service.activity;

import com.amazon.ata.gym.routine.service.exceptions.RoutineNotFoundException;
import com.amazon.ata.gym.routine.service.models.requests.GetRoutineRequest;
import com.amazon.ata.gym.routine.service.models.results.GetRoutineResult;
import com.amazon.ata.gym.routine.service.models.RoutineModel;
import com.amazon.ata.gym.routine.service.converters.ModelConverter;
import com.amazon.ata.gym.routine.service.dao.RoutineDao;
import com.amazon.ata.gym.routine.service.dynamodb.models.Routine;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;

/**
 * Implementation of the GetRoutineActivity for the GymRoutineService's GetRoutine API.
 *
 * This API allows the user to get one of their saved routines.
 */
public class GetRoutineActivity implements RequestHandler<GetRoutineRequest, GetRoutineResult> {
    private final Logger log = LogManager.getLogger();
    private final RoutineDao routineDao;

    /**
     * Instantiates a new GetRoutineActivity object.
     *
     * @param routineDao RoutineDao to access the routine table.
     */
    @Inject
    public GetRoutineActivity(RoutineDao routineDao) {
        this.routineDao = routineDao;
    }

    /**
     * This method handles the incoming request by retrieving the routine from the database.
     * <p>
     * It then returns the routine.
     * <p>
     * If the routine does not exist, this should throw a RoutineNotFoundException.
     *
     * @param getRoutineRequest request object containing the routine ID
     * @return getRoutineResult result object containing the API defined {@link RoutineModel}
     */
    @Override
    public GetRoutineResult handleRequest(final GetRoutineRequest getRoutineRequest, Context context) {
        log.info("Received GetRoutineRequest {}", getRoutineRequest);
        String requestedId = getRoutineRequest.getRoutineId();
        Routine routine = routineDao.getRoutine(requestedId);

        if (routine == null) {
            throw new RoutineNotFoundException("Could not find routine with id " + requestedId);
        }

        RoutineModel routineModel = new ModelConverter().toRoutineModel(routine);

        return GetRoutineResult.builder()
                .withRoutine(routineModel)
                .build();
    }
}

