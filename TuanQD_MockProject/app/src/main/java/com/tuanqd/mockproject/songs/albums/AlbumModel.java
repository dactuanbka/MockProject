package com.tuanqd.mockproject.songs.albums;

public class AlbumModel {
    private String imgAlbumUri;
    private String titleAlbum;
    private String artist;
    private int numberOfSongs;

    public AlbumModel(String imgAlbumUrl, String titleAlbum, String artist, int numberOfSongs) {
        this.imgAlbumUri = imgAlbumUrl;
        this.titleAlbum = titleAlbum;
        this.artist = artist;
        this.numberOfSongs = numberOfSongs;
    }

    public String getImgAlbumUri() {
        return imgAlbumUri;
    }

    public void setImgAlbumUri(String imgAlbumUri) {
        this.imgAlbumUri = imgAlbumUri;
    }

    public String getTitleAlbum() {
        return titleAlbum;
    }

    public void setTitleAlbum(String titleAlbum) {
        this.titleAlbum = titleAlbum;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public int getNumberOfSongs() {
        return numberOfSongs;
    }

    public void setNumberOfSongs(int numberOfSongs) {
        this.numberOfSongs = numberOfSongs;
    }
}
