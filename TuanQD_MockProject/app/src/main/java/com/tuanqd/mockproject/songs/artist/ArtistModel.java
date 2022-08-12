package com.tuanqd.mockproject.songs.artist;

import android.graphics.Bitmap;

public class ArtistModel {
private Bitmap imageArtist;
private String titleArtist;
private String numberAlbums;
private String numberSongs;

    public ArtistModel(Bitmap imageArtist, String titleArtist, String numberAlbums, String numberSongs) {
        this.imageArtist = imageArtist;
        this.titleArtist = titleArtist;
        this.numberAlbums = numberAlbums;
        this.numberSongs = numberSongs;
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

    public String getNumberAlbums() {
        return numberAlbums;
    }

    public void setNumberAlbums(String numberAlbums) {
        this.numberAlbums = numberAlbums;
    }

    public String getNumberSongs() {
        return numberSongs;
    }

    public void setNumberSongs(String numberSongs) {
        this.numberSongs = numberSongs;
    }
}
