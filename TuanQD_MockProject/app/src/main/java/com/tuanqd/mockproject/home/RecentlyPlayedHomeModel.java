package com.tuanqd.mockproject.home;

public class RecentlyPlayedHomeModel {
    private String imagePauseUri;
    private String title;
    private String singer;

    public RecentlyPlayedHomeModel(String imagePauseUri, String title, String singer) {
        this.imagePauseUri = imagePauseUri;
        this.title = title;
        this.singer = singer;
    }

    public String getImagePauseUri() {
        return imagePauseUri;
    }

    public void setImagePauseUri(String imagePauseUri) {
        this.imagePauseUri = imagePauseUri;
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
