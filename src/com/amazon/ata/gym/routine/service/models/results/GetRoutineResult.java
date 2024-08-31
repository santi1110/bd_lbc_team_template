package com.amazon.ata.gym.routine.service.models.results;

import com.amazon.ata.gym.routine.service.models.RoutineModel;

/**
 * Result class for retrieving a gym routine.
 */
public class GetRoutineResult {
    private RoutineModel routine; // The routine retrieved

    public GetRoutineResult(Builder builder) {
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

        public GetRoutineResult build() {
            return new GetRoutineResult(this);
        }
    }
}
