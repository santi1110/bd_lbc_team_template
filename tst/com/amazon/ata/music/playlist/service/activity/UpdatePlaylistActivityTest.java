package com.amazon.ata.music.playlist.service.activity;

import com.amazon.ata.music.playlist.service.dynamodb.PlaylistDao;
import com.amazon.ata.music.playlist.service.dynamodb.models.Playlist;
import com.amazon.ata.music.playlist.service.exceptions.InvalidAttributeChangeException;
import com.amazon.ata.music.playlist.service.exceptions.InvalidAttributeValueException;
import com.amazon.ata.music.playlist.service.exceptions.PlaylistNotFoundException;
import com.amazon.ata.music.playlist.service.models.requests.UpdatePlaylistRequest;
import com.amazon.ata.music.playlist.service.models.results.UpdatePlaylistResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class UpdatePlaylistActivityTest {
    @Mock
    private PlaylistDao playlistDao;
    //
    private UpdatePlaylistActivity updatePlaylistActivity;

    @BeforeEach
    public void setUp() {
        initMocks(this);
        updatePlaylistActivity = new UpdatePlaylistActivity(playlistDao);
    }



    @Test
    public void handleRequest_goodRequest_updatesPlaylistName() {
        // GIVEN
        String id = "id";
        String expectedCustomerId = "expectedCustomerId";
        String expectedName = "new name";

        UpdatePlaylistRequest request = UpdatePlaylistRequest.builder()
                                            .withId(id)
                                            .withCustomerId(expectedCustomerId)
                                            .withName(expectedName)
                                            .build();

        Playlist startingPlaylist = new Playlist();
        startingPlaylist.setCustomerId(expectedCustomerId);
        startingPlaylist.setName("old name");
        startingPlaylist.setSongCount(0);

        when(playlistDao.getPlaylist(id)).thenReturn(startingPlaylist);
        doAnswer(invocation -> {
            Playlist playlist = invocation.getArgument(0);
            // Simulate the DAO saving the playlist (update in place)
            startingPlaylist.setName(playlist.getName());
            startingPlaylist.setTags(playlist.getTags());
            return null;
        }).when(playlistDao).savePlaylist(any(Playlist.class));

        // WHEN
        UpdatePlaylistResult result = updatePlaylistActivity.handleRequest(request, null);

        // THEN
        assertEquals(expectedName, result.getPlaylist().getName());
        assertEquals(expectedCustomerId, result.getPlaylist().getCustomerId());

        verify(playlistDao, times(1)).getPlaylist(id);
        verify(playlistDao, times(1)).savePlaylist(startingPlaylist);
        verifyNoMoreInteractions(playlistDao);
    }



    @Test
    public void handleRequest_invalidName_throwsInvalidAttributeValueException() {
        // GIVEN
        UpdatePlaylistRequest request = UpdatePlaylistRequest.builder()
                                            .withId("id")
                                            .withName("I'm illegal")
                                            .withCustomerId("customerId")
                                            .build();

        // WHEN + THEN
        assertThrows(InvalidAttributeValueException.class, () -> updatePlaylistActivity.handleRequest(request, null));
    }

    @Test
    public void handleRequest_playlistDoesNotExist_throwsPlaylistNotFoundException() {
        // GIVEN
        String id = "id";
        UpdatePlaylistRequest request = UpdatePlaylistRequest.builder()
                                            .withId(id)
                                            .withName("name")
                                            .withCustomerId("customerId")
                                            .build();

        when(playlistDao.getPlaylist(id)).thenThrow(new PlaylistNotFoundException());

        // THEN
        assertThrows(PlaylistNotFoundException.class, () -> updatePlaylistActivity.handleRequest(request, null));
    }

    @Test
    public void handleRequest_customerIdNotMatch_throwsInvalidAttributeChangeException() {
        // GIVEN
        String id = "id";
        UpdatePlaylistRequest request = UpdatePlaylistRequest.builder()
                                            .withId(id)
                                            .withName("name")
                                            .withCustomerId("customerId")
                                            .build();

        Playlist differentCustomerIdPlaylist = new Playlist();
        differentCustomerIdPlaylist.setCustomerId("different");

        when(playlistDao.getPlaylist(id)).thenReturn(differentCustomerIdPlaylist);

        // THEN
        assertThrows(InvalidAttributeChangeException.class, () -> updatePlaylistActivity.handleRequest(request, null));
    }
}