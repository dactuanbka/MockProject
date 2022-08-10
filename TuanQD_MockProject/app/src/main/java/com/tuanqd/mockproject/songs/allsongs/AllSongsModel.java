package com.tuanqd.mockproject.songs.allsongs;

import android.graphics.Bitmap;

public class AllSongsModel {
    private String ID;
    private Bitmap bitmap;
    private String title;
    private String artist;
    private String duration;
    private String musicPath;

    public AllSongsModel(String ID, Bitmap bitmap, String title, String artist, String duration, String musicPath) {
        this.ID = ID;
        this.bitmap = bitmap;
        this.title = title;
        this.artist = artist;
        this.duration = duration;
        this.musicPath=musicPath;
    }

    public String getMusicPath() {
        return musicPath;
    }

    public void setMusicPath(String musicPath) {
        this.musicPath = musicPath;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
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
