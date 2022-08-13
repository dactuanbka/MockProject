package com.tuanqd.mockproject.main;

import android.graphics.Bitmap;

public class SongsModel {
    private String ID;
    private Bitmap bitmapSong;
    private String title;
    private String artist;
    private int artistId;
    private String albumId;
    private String album;
    private String duration;
    private String musicPath;

    public SongsModel(String ID, Bitmap bitmapSong, String title, String artist, int artistId,
                      String albumId, String album, String duration, String musicPath) {
        this.ID = ID;
        this.bitmapSong = bitmapSong;
        this.title = title;
        this.artist = artist;
        this.artistId = artistId;
        this.albumId = albumId;
        this.album = album;
        this.duration = duration;
        this.musicPath = musicPath;
    }

    public int getArtistId() {
        return artistId;
    }

    public void setArtistId(int artistId) {
        this.artistId = artistId;
    }

    public String getAlbumId() {
        return albumId;
    }

    public void setAlbumId(String albumId) {
        this.albumId = albumId;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getMusicPath() {
        return musicPath;
    }

    public void setMusicPath(String musicPath) {
        this.musicPath = musicPath;
    }

    public Bitmap getBitmapSong() {
        return bitmapSong;
    }

    public void setBitmapSong(Bitmap bitmapSong) {
        this.bitmapSong = bitmapSong;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }
}
