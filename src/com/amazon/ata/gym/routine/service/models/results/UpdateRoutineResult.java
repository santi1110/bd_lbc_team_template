package com.amazon.ata.gym.routine.service.models.results;

import com.amazon.ata.gym.routine.service.models.RoutineModel;

/**
 * Result class for updating a gym routine.
 */
public class UpdateRoutineResult {
    private RoutineModel routine; // Updated to reflect the new domain model

    public UpdateRoutineResult(Builder builder) {
        this.routine = builder.routine;
    }

    public RoutineModel getRoutine() {
        return routine;
    }

    public void setRoutine(RoutineModel routine) {
        this.routine = routine;
    }

    public static Builder builder() { return new Builder(); }

    public static final class Builder {
        private RoutineModel routine;

        public Builder withRoutine(RoutineModel routineToUse) {
            this.routine = routineToUse;
            return this;
        }

        public UpdateRoutineResult build() {
            return new UpdateRoutineResult(this);
        }
    }
}

