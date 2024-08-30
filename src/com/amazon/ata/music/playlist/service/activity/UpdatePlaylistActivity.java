package com.amazon.ata.music.playlist.service.activity;

import com.amazon.ata.music.playlist.service.converters.ModelConverter;
import com.amazon.ata.music.playlist.service.dynamodb.models.Playlist;
import com.amazon.ata.music.playlist.service.exceptions.InvalidAttributeChangeException;
import com.amazon.ata.music.playlist.service.exceptions.InvalidAttributeValueException;
import com.amazon.ata.music.playlist.service.models.PlaylistModel;
import com.amazon.ata.music.playlist.service.models.requests.UpdatePlaylistRequest;
import com.amazon.ata.music.playlist.service.models.results.UpdatePlaylistResult;
import com.amazon.ata.music.playlist.service.dynamodb.PlaylistDao;

import com.amazon.ata.music.playlist.service.util.MusicPlaylistServiceUtils;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;
import java.util.HashSet;

/**
 * Implementation of the UpdatePlaylistActivity for the MusicPlaylistService's UpdatePlaylist API.
 *
 * This API allows the customer to update their saved playlist's information.
 */
public class UpdatePlaylistActivity implements RequestHandler<UpdatePlaylistRequest, UpdatePlaylistResult> {
    private final Logger log = LogManager.getLogger();
    private final PlaylistDao playlistDao;

    /**
     * Instantiates a new UpdatePlaylistActivity object.
     *
     * @param playlistDao PlaylistDao to access the playlist table.
     */
    @Inject
    public UpdatePlaylistActivity(PlaylistDao playlistDao) {
        this.playlistDao = playlistDao;
    }

    /**
     * This method handles the incoming request by retrieving the playlist, updating it,
     * and persisting the playlist.
     * <p>
     * It then returns the updated playlist.
     * <p>
     * If the playlist does not exist, this should throw a PlaylistNotFoundException.
     * <p>
     * If the provided playlist name or customer ID has invalid characters, throws an
     * InvalidAttributeValueException
     * <p>
     * If the request tries to update the customer ID,
     * this should throw an InvalidAttributeChangeException
     *
     * @param updatePlaylistRequest request object containing the playlist ID, playlist name, and customer ID
     *                              associated with it
     * @return updatePlaylistResult result object containing the API defined {@link PlaylistModel}
     */
    @Override
    public UpdatePlaylistResult handleRequest(final UpdatePlaylistRequest updatePlaylistRequest, Context context) {
        log.info("Received UpdatePlaylistRequest {}", updatePlaylistRequest);

        String playlistId = updatePlaylistRequest.getId();
        Playlist existingPlaylist = playlistDao.getPlaylist(playlistId);

        // Validate the request and perform updates
        validateRequest(updatePlaylistRequest, existingPlaylist);
        updatePlaylist(existingPlaylist, updatePlaylistRequest);

        // Save the updated playlist back to the database
        playlistDao.savePlaylist(existingPlaylist);

        // Convert the updated playlist to a model for result
        PlaylistModel updatedPlaylistModel = new ModelConverter().toPlaylistModel(existingPlaylist);


        return UpdatePlaylistResult.builder()
                .withPlaylist(updatedPlaylistModel)
                .build();
    }

    private void validateRequest(UpdatePlaylistRequest updateRequest, Playlist existingPlaylist) {
        validateAttributes(updateRequest, existingPlaylist);
        validateAttributeChanges(updateRequest, existingPlaylist);
    }

    private void validateAttributes(UpdatePlaylistRequest updateRequest, Playlist existingPlaylist) {
        if (!MusicPlaylistServiceUtils.isValidString(updateRequest.getName()) || !MusicPlaylistServiceUtils.isValidString(updateRequest.getCustomerId())) {
            throw new InvalidAttributeValueException("Invalid characters in playlist name or customer ID");
        }
    }

    private void validateAttributeChanges(UpdatePlaylistRequest updateRequest, Playlist existingPlaylist) {
        if (!existingPlaylist.getCustomerId().equals(updateRequest.getCustomerId())) {
            throw new InvalidAttributeChangeException("Customer ID cannot be changed");
        }
    }

    private void updatePlaylist(Playlist existingPlaylist, UpdatePlaylistRequest updateRequest) {
        if (updateRequest.getName() != null) {
            existingPlaylist.setName(updateRequest.getName());
        }
    }}