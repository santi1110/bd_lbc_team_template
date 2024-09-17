package com.amazon.ata.gym.routine.service.activity;

import com.amazon.ata.gym.routine.service.converters.ModelConverter;
import com.amazon.ata.gym.routine.service.dynamodb.models.Routine;
import com.amazon.ata.gym.routine.service.exceptions.InvalidAttributeChangeException;
import com.amazon.ata.gym.routine.service.exceptions.InvalidAttributeValueException;
import com.amazon.ata.gym.routine.service.models.RoutineModel;
import com.amazon.ata.gym.routine.service.models.requests.UpdateRoutineRequest;
import com.amazon.ata.gym.routine.service.models.results.UpdateRoutineResult;
import com.amazon.ata.gym.routine.service.dao.RoutineDao;

import com.amazon.ata.gym.routine.service.util.GymRoutineServiceUtils;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;

/**
 * Implementation of the UpdateRoutineActivity for the GymRoutineService's UpdateRoutine API.
 *
 * This API allows the user to update their saved routine's information.
 */
public class UpdateRoutineActivity implements RequestHandler<UpdateRoutineRequest, UpdateRoutineResult> {
    private final Logger log = LogManager.getLogger();
    private final RoutineDao routineDao;

    /**
     * Instantiates a new UpdateRoutineActivity object.
     *
     * @param routineDao RoutineDao to access the routine table.
     */
    @Inject
    public UpdateRoutineActivity(RoutineDao routineDao) {
        this.routineDao = routineDao;
    }

    /**
     * This method handles the incoming request by retrieving the routine, updating it,
     * and persisting the routine.
     * <p>
     * It then returns the updated routine.
     * <p>
     * If the routine does not exist, this should throw a RoutineNotFoundException.
     * <p>
     * If the provided routine name or customer ID has invalid characters, throws an
     * InvalidAttributeValueException
     * <p>
     * If the request tries to update the customer ID,
     * this should throw an InvalidAttributeChangeException
     *
     * @param updateRoutineRequest request object containing the routine ID, routine name, and customer ID
     *                              associated with it
     * @return updateRoutineResult result object containing the API defined {@link RoutineModel}
     */
    @Override
    public UpdateRoutineResult handleRequest(final UpdateRoutineRequest updateRoutineRequest, Context context) {
        log.info("Received UpdateRoutineRequest {}", updateRoutineRequest);

        String routineId = updateRoutineRequest.getId();
        Routine existingRoutine = routineDao.getRoutine(routineId);

        // Validate the request and perform updates
        validateRequest(updateRoutineRequest, existingRoutine);
        updateRoutine(existingRoutine, updateRoutineRequest);

        // Save the updated routine back to the database
        routineDao.saveRoutine(existingRoutine);

        // Convert the updated routine to a model for result
        RoutineModel updatedRoutineModel = new ModelConverter().toRoutineModel(existingRoutine);

        return UpdateRoutineResult.builder()
                .withRoutine(updatedRoutineModel)
                .build();
    }

    private void validateRequest(UpdateRoutineRequest updateRequest, Routine existingRoutine) {
        validateAttributes(updateRequest, existingRoutine);
        validateAttributeChanges(updateRequest, existingRoutine);
    }

    private void validateAttributes(UpdateRoutineRequest updateRequest, Routine existingRoutine) {
        if (!GymRoutineServiceUtils.isValidString(updateRequest.getName()) || !GymRoutineServiceUtils.isValidString(updateRequest.getCustomerId())) {
            throw new InvalidAttributeValueException("Invalid characters in routine name or customer ID");
        }
    }

    private void validateAttributeChanges(UpdateRoutineRequest updateRequest, Routine existingRoutine) {
        if (!existingRoutine.getCustomerId().equals(updateRequest.getCustomerId())) {
            throw new InvalidAttributeChangeException("Customer ID cannot be changed");
        }
    }

    private void updateRoutine(Routine existingRoutine, UpdateRoutineRequest updateRequest) {
        if (updateRequest.getName() != null) {
            existingRoutine.setName(updateRequest.getName());
        }
    }
}
