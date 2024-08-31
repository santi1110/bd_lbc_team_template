package com.amazon.ata.gym.routine.service.activity;

import com.amazon.ata.gym.routine.service.dynamodb.ExerciseDao;
import com.amazon.ata.gym.routine.service.dynamodb.RoutineDao;
import com.amazon.ata.gym.routine.service.dynamodb.models.Exercise;
import com.amazon.ata.gym.routine.service.dynamodb.models.Routine;
import com.amazon.ata.gym.routine.service.exceptions.ExerciseNotFoundException;
import com.amazon.ata.gym.routine.service.exceptions.RoutineNotFoundException;
import com.amazon.ata.gym.routine.service.models.ExerciseModel;
import com.amazon.ata.gym.routine.service.models.requests.AddExerciseToRoutineRequest;
import com.amazon.ata.gym.routine.service.models.results.AddExerciseToRoutineResult;
import com.amazon.ata.gym.routine.service.helpers.ExerciseTestHelper;
import com.amazon.ata.gym.routine.service.helpers.RoutineTestHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class AddExerciseToRoutineActivityTest {
    @Mock
    private RoutineDao routineDao;

    @Mock
    private ExerciseDao exerciseDao;

    private AddExerciseToRoutineActivity addExerciseToRoutineActivity;

    @BeforeEach
    public void setup() {
        initMocks(this);
        addExerciseToRoutineActivity = new AddExerciseToRoutineActivity(routineDao, exerciseDao);
    }

    @Test
    void handleRequest_validRequest_addsExerciseToEndOfRoutine() {
        // GIVEN
        // a non-empty routine
        Routine originalRoutine = RoutineTestHelper.generateRoutine();
        String routineId = originalRoutine.getId();

        // the new exercise to add to the routine
        Exercise exerciseToAdd = ExerciseTestHelper.generateExercise(2);
        String addedExerciseId = exerciseToAdd.getExerciseId();
        int addedTrackNumber = exerciseToAdd.getTrackNumber();

        when(routineDao.getRoutine(routineId)).thenReturn(originalRoutine);
        when(exerciseDao.getExercise(addedExerciseId, addedTrackNumber)).thenReturn(exerciseToAdd);

        AddExerciseToRoutineRequest request = AddExerciseToRoutineRequest.builder()
                .withId(routineId)
                .withExerciseId(addedExerciseId)
                .withTrackNumber(addedTrackNumber)
                .build();

        // WHEN
        AddExerciseToRoutineResult result = addExerciseToRoutineActivity.handleRequest(request, null);

        // THEN
        verify(routineDao).saveRoutine(originalRoutine);

        assertEquals(2, result.getExerciseList().size());
        ExerciseModel secondExercise = result.getExerciseList().get(1);
        ExerciseTestHelper.assertExerciseEqualsExerciseModel(exerciseToAdd, secondExercise);
    }

    @Test
    public void handleRequest_noMatchingRoutineId_throwsRoutineNotFoundException() {
        // GIVEN
        String routineId = "missing id";
        AddExerciseToRoutineRequest request = AddExerciseToRoutineRequest.builder()
                .withId(routineId)
                .withExerciseId("exerciseId")
                .withTrackNumber(1)
                .build();
        when(routineDao.getRoutine(routineId)).thenThrow(new RoutineNotFoundException());

        // WHEN + THEN
        assertThrows(RoutineNotFoundException.class, () -> addExerciseToRoutineActivity.handleRequest(request, null));
    }

    @Test
    public void handleRequest_noMatchingExercise_throwsExerciseNotFoundException() {
        // GIVEN
        Routine routine = RoutineTestHelper.generateRoutine();
        String routineId = routine.getId();
        String exerciseId = "nonexistent exerciseId";
        int trackNumber = -1;
        AddExerciseToRoutineRequest request = AddExerciseToRoutineRequest.builder()
                .withId(routineId)
                .withExerciseId(exerciseId)
                .withTrackNumber(trackNumber)
                .build();

        // WHEN
        when(routineDao.getRoutine(routineId)).thenReturn(routine);
        when(exerciseDao.getExercise(exerciseId, trackNumber)).thenThrow(new ExerciseNotFoundException());

        // THEN
        assertThrows(ExerciseNotFoundException.class, () -> addExerciseToRoutineActivity.handleRequest(request, null));
    }
}


    /*@Test
    void handleRequest_validRequestWithQueueNextFalse_addsSongToEndOfPlaylist() {
        // GIVEN
        int startingTrackCount = 3;
        Playlist originalPlaylist = PlaylistTestHelper.generatePlaylistWithNAlbumTracks(startingTrackCount);
        String playlistId = originalPlaylist.getId();

        // the new song to add to the playlist
        AlbumTrack albumTrackToAdd = AlbumTrackTestHelper.generateAlbumTrack(8);
        String addedAsin = albumTrackToAdd.getAsin();
        int addedTracknumber = albumTrackToAdd.getTrackNumber();

        when(playlistDao.getPlaylist(playlistId)).thenReturn(originalPlaylist);
        when(playlistDao.savePlaylist(originalPlaylist)).thenReturn(originalPlaylist);
        when(albumTrackDao.getAlbumTrack(addedAsin, addedTracknumber)).thenReturn(albumTrackToAdd);

        AddSongToPlaylistRequest request = AddSongToPlaylistRequest.builder()
                                               .withId(playlistId)
                                               .withAsin(addedAsin)
                                               .withTrackNumber(addedTracknumber)
                                               .withQueueNext(false)
                                               .build();

        // WHEN
        AddSongToPlaylistResult result = addSongToPlaylistActivity.handleRequest(request, null);

        // THEN
        verify(playlistDao).savePlaylist(originalPlaylist);

        assertEquals(startingTrackCount + 1, result.getSongList().size());
        SongModel lastSong = result.getSongList().get(result.getSongList().size() - 1);
        AlbumTrackTestHelper.assertAlbumTrackEqualsSongModel(albumTrackToAdd, lastSong);
    }

    @Test
    void handleRequest_validRequestWithQueueNextTrue_addsSongToBeginningOfPlaylist() {
        // GIVEN
        int startingPlaylistSize = 2;
        Playlist originalPlaylist = PlaylistTestHelper.generatePlaylistWithNAlbumTracks(startingPlaylistSize);
        String playlistId = originalPlaylist.getId();

        // the new song to add to the playlist
        AlbumTrack albumTrackToAdd = AlbumTrackTestHelper.generateAlbumTrack(6);
        String addedAsin = albumTrackToAdd.getAsin();
        int addedTracknumber = albumTrackToAdd.getTrackNumber();

        when(playlistDao.getPlaylist(playlistId)).thenReturn(originalPlaylist);
        when(playlistDao.savePlaylist(originalPlaylist)).thenReturn(originalPlaylist);
        when(albumTrackDao.getAlbumTrack(addedAsin, addedTracknumber)).thenReturn(albumTrackToAdd);

        AddSongToPlaylistRequest request = AddSongToPlaylistRequest.builder()
                                               .withId(playlistId)
                                               .withAsin(addedAsin)
                                               .withTrackNumber(addedTracknumber)
                                               .withQueueNext(true)
                                               .build();

        // WHEN
        AddSongToPlaylistResult result = addSongToPlaylistActivity.handleRequest(request, null);

        // THEN
        verify(playlistDao).savePlaylist(originalPlaylist);

        assertEquals(startingPlaylistSize + 1, result.getSongList().size());
        SongModel firstSong = result.getSongList().get(0);
        AlbumTrackTestHelper.assertAlbumTrackEqualsSongModel(albumTrackToAdd, firstSong);
    }*/
}