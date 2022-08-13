package com.tuanqd.mockproject.songs.playlist;

import android.graphics.Bitmap;

import com.tuanqd.mockproject.main.SongsModel;

public class PlaylistSongsModel extends SongsModel {
    private String namePlaylist;

    public PlaylistSongsModel(String ID, Bitmap bitmapSong, String title, String artist,int artistId, String albumId,
                              String album, String duration, String musicPath,String namePlaylist) {
        super(ID, bitmapSong, title, artist, artistId, albumId, album, duration, musicPath);
        this.namePlaylist= namePlaylist;
    }

    public String getNamePlaylist() {
        return namePlaylist;
    }

    public void setNamePlaylist(String namePlaylist) {
        this.namePlaylist = namePlaylist;
    }
}
