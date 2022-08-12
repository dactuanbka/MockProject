package com.tuanqd.mockproject.songs.artist.artistdetails;

public class TopSongsModel {
    private int imgTopSongResource;
    private String titleSong;
    private String duration;
    private int imgMenuTopSongRes;

    public TopSongsModel(int imgTopSongResource, String titleSong, String duration, int imgMenuTopSongRes) {
        this.imgTopSongResource = imgTopSongResource;
        this.titleSong = titleSong;
        this.duration = duration;
        this.imgMenuTopSongRes = imgMenuTopSongRes;
    }

    public int getImgTopSongResource() {
        return imgTopSongResource;
    }

    public void setImgTopSongResource(int imgTopSongResource) {
        this.imgTopSongResource = imgTopSongResource;
    }

    public String getTitleSong() {
        return titleSong;
    }

    public void setTitleSong(String titleSong) {
        this.titleSong = titleSong;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public int getImgMenuTopSongRes() {
        return imgMenuTopSongRes;
    }

    public void setImgMenuTopSongRes(int imgMenuTopSongRes) {
        this.imgMenuTopSongRes = imgMenuTopSongRes;
    }
}
