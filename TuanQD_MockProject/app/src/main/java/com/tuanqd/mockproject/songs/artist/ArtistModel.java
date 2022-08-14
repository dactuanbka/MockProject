package com.tuanqd.mockproject.songs.artist;

import android.graphics.Bitmap;

public class ArtistModel {
private Bitmap imageArtistBitmap;
private String titleArtist;
private int numberAlbums;
private int numberSongs;

    public ArtistModel(Bitmap imageArtistBitmap, String titleArtist, int numberAlbums, int numberSongs) {
        this.imageArtistBitmap = imageArtistBitmap;
        this.titleArtist = titleArtist;
        this.numberAlbums = numberAlbums;
        this.numberSongs = numberSongs;
    }
    public ArtistModel() {
    }

    public Bitmap getImageArtistBitmap() {
        return imageArtistBitmap;
    }

    public void setImageArtistBitmap(Bitmap imageArtistBitmap) {
        this.imageArtistBitmap = imageArtistBitmap;
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
