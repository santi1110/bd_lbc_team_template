package com.amazon.ata.gym.routine.service.models.results;

import com.amazon.ata.gym.routine.service.models.PlaylistModel;

public class GetPlaylistResult {
    private PlaylistModel playlist;

    public GetPlaylistResult(Builder builder) {
        this.playlist = builder.playlist;
    }

    public PlaylistModel getPlaylist() {
        return playlist;
    }

    public void setPlaylist(PlaylistModel playlist) {
        this.playlist = playlist;
    }

    public static Builder builder() {return new Builder();}

    public static final class Builder {
        private PlaylistModel playlist;

        public Builder withPlaylist(PlaylistModel playlistToUse) {
            this.playlist = playlistToUse;
            return this;
        }

        public GetPlaylistResult build() {return new GetPlaylistResult(this);}
    }
}
