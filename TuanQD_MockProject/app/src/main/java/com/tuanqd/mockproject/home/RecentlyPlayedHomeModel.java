package com.tuanqd.mockproject.home;

public class RecentlyPlayedHomeModel {
    private int imagePause;
    private String title;
    private String singer;
    private int heartImageRes;
    private int rateImageRes;

    public RecentlyPlayedHomeModel(int imagePause, String title, String singer, int heartImageRes, int rateImageRes) {
        this.imagePause = imagePause;
        this.title = title;
        this.singer = singer;
        this.heartImageRes = heartImageRes;
        this.rateImageRes = rateImageRes;
    }

    public int getImagePause() {
        return imagePause;
    }

    public void setImagePause(int imagePause) {
        this.imagePause = imagePause;
    }

    public int getHeartImageRes() {
        return heartImageRes;
    }

    public void setHeartImageRes(int heartImageRes) {
        this.heartImageRes = heartImageRes;
    }

    public int getRateImageRes() {
        return rateImageRes;
    }

    public void setRateImageRes(int rateImageRes) {
        this.rateImageRes = rateImageRes;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }
}
