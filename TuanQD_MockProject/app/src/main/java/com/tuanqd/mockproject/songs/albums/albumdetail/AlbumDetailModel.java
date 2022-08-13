package com.tuanqd.mockproject.songs.albums.albumdetail;

public class AlbumDetailModel {
    private int iconPlayAlBumRes;
    private String titleSongOfAlbum;
    private String duration;
    private int imgPlayAlbumDetailRes;

    public AlbumDetailModel(int iconPlayAlBumRes, String titleSongOfAlbum, String duration, int imgPlayAlbumDetailRes) {
        this.iconPlayAlBumRes = iconPlayAlBumRes;
        this.titleSongOfAlbum = titleSongOfAlbum;
        this.duration = duration;
        this.imgPlayAlbumDetailRes = imgPlayAlbumDetailRes;
    }

    public int getIconPlayAlBumRes() {
        return iconPlayAlBumRes;
    }

    public void setIconPlayAlBumRes(int iconPlayAlBumRes) {
        this.iconPlayAlBumRes = iconPlayAlBumRes;
    }

    public String getTitleSongOfAlbum() {
        return titleSongOfAlbum;
    }

    public void setTitleSongOfAlbum(String titleSongOfAlbum) {
        this.titleSongOfAlbum = titleSongOfAlbum;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public int getImgPlayAlbumDetailRes() {
        return imgPlayAlbumDetailRes;
    }

    public void setImgPlayAlbumDetailRes(int imgPlayAlbumDetailRes) {
        this.imgPlayAlbumDetailRes = imgPlayAlbumDetailRes;
    }
}
