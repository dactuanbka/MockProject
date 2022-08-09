package com.tuanqd.mockproject.home;

public class PlaylistHomeModel {
    private int imageResource;
    private String bigText;
    private String smallText;

    public PlaylistHomeModel(int imageResource, String bigText, String smallText) {
        this.imageResource = imageResource;
        this.bigText = bigText;
        this.smallText = smallText;
    }

    public int getImageResource() {
        return imageResource;
    }

    public void setImageResource(int imageResource) {
        this.imageResource = imageResource;
    }

    public String getBigText() {
        return bigText;
    }

    public void setBigText(String bigText) {
        this.bigText = bigText;
    }

    public String getSmallText() {
        return smallText;
    }

    public void setSmallText(String smallText) {
        this.smallText = smallText;
    }
}
