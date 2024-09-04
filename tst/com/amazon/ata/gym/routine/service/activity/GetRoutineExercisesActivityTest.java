package com.amazon.ata.gym.routine.service.activity;

import com.amazon.ata.gym.routine.service.dao.RoutineDao;
import com.amazon.ata.gym.routine.service.dynamodb.models.Routine;
import com.amazon.ata.gym.routine.service.exceptions.RoutineNotFoundException;
import com.amazon.ata.gym.routine.service.models.requests.GetRoutineExercisesRequest;
import com.amazon.ata.gym.routine.service.models.results.GetRoutineExercisesResult;
import com.amazon.ata.gym.routine.service.helpers.ExerciseTestHelper;
import com.amazon.ata.gym.routine.service.helpers.RoutineTestHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class GetRoutineExercisesActivityTest {
    @Mock
    private RoutineDao routineDao;

    private GetRoutineExercisesActivity getRoutineExercisesActivity;

    @BeforeEach
    public void setup() {
        initMocks(this);
        getRoutineExercisesActivity = new GetRoutineExercisesActivity(routineDao);
    }

    @Test
    void handleRequest_routineExistsWithExercises_returnsExercisesInRoutine() {
        // GIVEN
        Routine routine = RoutineTestHelper.generateRoutineWithNExercises(3);
        String routineId = routine.getId();
        GetRoutineExercisesRequest request = GetRoutineExercisesRequest.builder()
                .withRoutineId(routineId)
                .build();
        when(routineDao.getRoutine(routineId)).thenReturn(routine);

        // WHEN
        GetRoutineExercisesResult result = getRoutineExercisesActivity.handleRequest(request, null);

        // THEN
        ExerciseTestHelper.assertExercisesEqualExerciseModels(routine.getExerciseList(), result.getExerciseList());
    }

    @Test
    void handleRequest_routineExistsWithoutExercises_returnsEmptyList() {
        // GIVEN
        Routine emptyRoutine = RoutineTestHelper.generateRoutineWithNExercises(0);
        String routineId = emptyRoutine.getId();
        GetRoutineExercisesRequest request = GetRoutineExercisesRequest.builder()
                .withRoutineId(routineId)
                .build();
        when(routineDao.getRoutine(routineId)).thenReturn(emptyRoutine);

        // WHEN
        GetRoutineExercisesResult result = getRoutineExercisesActivity.handleRequest(request, null);

        // THEN
        assertTrue(result.getExerciseList().isEmpty(),
                "Expected exercise list to be empty but was " + result.getExerciseList());
    }

    @Test
    public void handleRequest_noMatchingRoutineId_throwsRoutineNotFoundException() {
        // GIVEN
        String id = "missingID";
        GetRoutineExercisesRequest request = GetRoutineExercisesRequest.builder()
                .withRoutineId(id)
                .build();

        // WHEN
        when(routineDao.getRoutine(id)).thenThrow(new RoutineNotFoundException());

        // WHEN + THEN
        assertThrows(RoutineNotFoundException.class, () -> getRoutineExercisesActivity.handleRequest(request, null));
    }
}


/*    @Test
    void handleRequest_withDefaultSongOrder_returnsDefaultOrderedPlaylistSongs() {
        // GIVEN
        Playlist playlist = PlaylistTestHelper.generatePlaylistWithNAlbumTracks(10);
        String playlistId = playlist.getId();

        GetPlaylistSongsRequest request = GetPlaylistSongsRequest.builder()
                                              .withId(playlistId)
                                              .withOrder(SongOrder.DEFAULT)
                                              .build();
        when(playlistDao.getPlaylist(playlistId)).thenReturn(playlist);

        // WHEN
        GetPlaylistSongsResult result = getPlaylistSongsActivity.handleRequest(request, null);

        // THEN
        AlbumTrackTestHelper.assertAlbumTracksEqualSongModels(playlist.getSongList(), result.getSongList());
    }*/

/*    @Test
    void handleRequest_withReversedSongOrder_returnsReversedPlaylistSongs() {
        // GIVEN
        Playlist playlist = PlaylistTestHelper.generatePlaylistWithNAlbumTracks(9);
        String playlistId = playlist.getId();
        List<AlbumTrack> reversedAlbumTracks = new LinkedList<>(playlist.getSongList());
        Collections.reverse(reversedAlbumTracks);

        GetPlaylistSongsRequest request = GetPlaylistSongsRequest.builder()
                                              .withId(playlistId)
                                              .withOrder(SongOrder.REVERSED)
                                              .build();
        when(playlistDao.getPlaylist(playlistId)).thenReturn(playlist);

        // WHEN
        GetPlaylistSongsResult result = getPlaylistSongsActivity.handleRequest(request, null);

        // THEN
        AlbumTrackTestHelper.assertAlbumTracksEqualSongModels(reversedAlbumTracks, result.getSongList());
    }*/

 /*   @Test
    void handleRequest_withShuffledSongOrder_returnsSongsInAnyOrder() {
        Playlist playlist = PlaylistTestHelper.generatePlaylistWithNAlbumTracks(8);
        String playlistId = playlist.getId();

        List<SongModel> songModels = new ModelConverter().toSongModelList(playlist.getSongList());

        GetPlaylistSongsRequest request = GetPlaylistSongsRequest.builder()
                                              .withId(playlistId)
                                              .withOrder(SongOrder.REVERSED)
                                              .build();
        when(playlistDao.getPlaylist(playlistId)).thenReturn(playlist);

        // WHEN
        GetPlaylistSongsResult result = getPlaylistSongsActivity.handleRequest(request, null);

        // THEN
        assertEquals(playlist.getSongList().size(),
                     result.getSongList().size(),
                     String.format("Expected album tracks (%s) and song models (%s) to be the same length",
                                   playlist.getSongList(),
                                   result.getSongList()));
        assertTrue(
            songModels.containsAll(result.getSongList()),
            String.format("album list (%s) and song models (%s) are the same length, but don't contain the same " +
                          "entries. Expected song models: %s. Returned song models: %s",
                          playlist.getSongList(),
                          result.getSongList(),
                          songModels,
                          result.getSongList()));
    }*/

/*    @Test
    public void handleRequest_noMatchingPlaylistId_throwsPlaylistNotFoundException() {
        // GIVEN
        String id = "missingID";
        GetPlaylistSongsRequest request = GetPlaylistSongsRequest.builder()
                                              .withId(id)
                                              .build();

        // WHEN
        when(playlistDao.getPlaylist(id)).thenThrow(new PlaylistNotFoundException());

        // WHEN + THEN
        assertThrows(PlaylistNotFoundException.class, () -> getPlaylistSongsActivity.handleRequest(request, null));
    }*/

//    @Test
//    public void handleRequest_withInvalidSongOrder_throwsException() {
//        // GIVEN
//        Playlist playlist = PlaylistTestHelper.generatePlaylist();
//        String id = playlist.getId();
//        GetPlaylistSongsRequest request = GetPlaylistSongsRequest.builder()
//            .withId(id)
//            .withOrder("NOT A VALID ORDER")
//            .build();
//
//        // WHEN + THEN
//        assertThrows(IllegalArgumentException.class, () -> getPlaylistSongsActivity.handleRequest(request));
//    }
