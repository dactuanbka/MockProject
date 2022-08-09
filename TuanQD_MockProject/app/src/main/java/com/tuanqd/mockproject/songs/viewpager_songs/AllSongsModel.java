package com.tuanqd.mockproject.songs.viewpager_songs;

public class AllSongsModel {
    private String ID;
    private int thumbs;
    private String title;
    private String artist;
    private String duration;

    public AllSongsModel(String ID, int thumbs, String title, String artist, String duration) {
        this.ID = ID;
        this.thumbs = thumbs;
        this.title = title;
        this.artist = artist;
        this.duration = duration;
    }

    public int getThumbs() {
        return thumbs;
    }

    public void setThumbs(int thumbs) {
        this.thumbs = thumbs;
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
