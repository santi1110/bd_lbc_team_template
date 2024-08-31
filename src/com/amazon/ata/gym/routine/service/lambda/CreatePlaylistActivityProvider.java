package com.amazon.ata.gym.routine.service.lambda;

import com.amazon.ata.gym.routine.service.activity.CreatePlaylistActivity;
import com.amazon.ata.music.playlist.service.dependency.DaggerServiceComponent;
import com.amazon.ata.gym.routine.service.dependency.ServiceComponent;
import com.amazon.ata.gym.routine.service.models.requests.CreatePlaylistRequest;
import com.amazon.ata.gym.routine.service.models.results.CreatePlaylistResult;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class CreatePlaylistActivityProvider implements RequestHandler<CreatePlaylistRequest, CreatePlaylistResult> {

    private static ServiceComponent serviceComponent;

    private CreatePlaylistActivity createPlaylistActivity;

    public CreatePlaylistActivityProvider() {
        if (serviceComponent == null) {
            serviceComponent = DaggerServiceComponent.create();
        }
        createPlaylistActivity = serviceComponent.provideCreatePlaylistActivity();
    }

    @Override
    public CreatePlaylistResult handleRequest(final CreatePlaylistRequest createPlaylistRequest, Context context) {
        return createPlaylistActivity.handleRequest(createPlaylistRequest, context);
    }
}

