package com.amazon.ata.music.playlist.service.dependency;

import com.amazon.ata.music.playlist.service.activity.*;
import com.amazon.ata.music.playlist.service.lambda.*;
import dagger.Component;

import javax.inject.Singleton;

@Singleton
@Component(modules = {DaoModule.class})
public interface ServiceComponent {
    void inject(AddSongToPlaylistActivityProvider addSongToPlaylistActivityProvider);
    void inject(CreatePlaylistActivityProvider createPlaylistActivityProvider);
    void inject(GetPlaylistActivityProvider getPlaylistActivityProvider);
    void inject(GetPlaylistSongsActivityProvider getPlaylistSongsActivityProvider);
    void inject(UpdatePlaylistActivityProvider updatePlaylistActivityProvider);

    AddSongToPlaylistActivity provideAddSongToPlaylistActivity();
    CreatePlaylistActivity provideCreatePlaylistActivity();
    GetPlaylistActivity provideGetPlaylistActivity();
    GetPlaylistSongsActivity provideGetPlaylistSongsActivity();
    UpdatePlaylistActivity provideUpdatePlaylistActivity();
}
