package com.tuanqd.mockproject.songs.viewpager_songs;

import android.graphics.Bitmap;

public class AllSongsModel {
    private String ID;
    private Bitmap bitmap;
    private String title;
    private String artist;
    private String duration;

    public AllSongsModel(String ID, Bitmap bitmap, String title, String artist, String duration) {
        this.ID = ID;
        this.bitmap = bitmap;
        this.title = title;
        this.artist = artist;
        this.duration = duration;
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
