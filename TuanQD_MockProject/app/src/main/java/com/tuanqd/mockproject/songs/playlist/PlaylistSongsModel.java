package com.tuanqd.mockproject.songs.playlist;

import android.graphics.Bitmap;

import com.tuanqd.mockproject.songs.allsongs.SongsModel;

public class PlaylistSongsModel extends SongsModel {
    private String namePlaylist;

    public PlaylistSongsModel(String ID, Bitmap bitmap, String title, String artist, String duration, String musicPath, String namePlaylist) {
        super(ID, bitmap, title, artist, duration, musicPath);
        this.namePlaylist = namePlaylist;
    }

    public String getNamePlaylist() {
        return namePlaylist;
    }

    public void setNamePlaylist(String namePlaylist) {
        this.namePlaylist = namePlaylist;
    }
}
