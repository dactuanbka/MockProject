package com.tuanqd.mockproject.songs.artist;

import android.graphics.Bitmap;

public class ArtistModel {
private Bitmap imageArtist;
private String titleArtist;
private int numberAlbums;
private int numberSongs;

    public ArtistModel(Bitmap imageArtist, String titleArtist, int numberAlbums, int numberSongs) {
        this.imageArtist = imageArtist;
        this.titleArtist = titleArtist;
        this.numberAlbums = numberAlbums;
        this.numberSongs = numberSongs;
    }
    public ArtistModel() {
    }

    public Bitmap getImageArtist() {
        return imageArtist;
    }

    public void setImageArtist(Bitmap imageArtist) {
        this.imageArtist = imageArtist;
    }

    public String getTitleArtist() {
        return titleArtist;
    }

    public void setTitleArtist(String titleArtist) {
        this.titleArtist = titleArtist;
    }

    public int getNumberAlbums() {
        return numberAlbums;
    }

    public void setNumberAlbums(int numberAlbums) {
        this.numberAlbums = numberAlbums;
    }

    public int getNumberSongs() {
        return numberSongs;
    }

    public void setNumberSongs(int numberSongs) {
        this.numberSongs = numberSongs;
    }
}
