package com.tuanqd.mockproject.songs.artist.artistdetails;

public class TopAlbumsModel {
    private int imgTopAlbumsResource;
    private String textItem0;
    private String textItem1;

    public TopAlbumsModel(int imgTopAlbumsResource, String textItem0, String textItem1) {
        this.imgTopAlbumsResource = imgTopAlbumsResource;
        this.textItem0 = textItem0;
        this.textItem1 = textItem1;
    }

    public int getImgTopAlbumsResource() {
        return imgTopAlbumsResource;
    }

    public void setImgTopAlbumsResource(int imgTopAlbumsResource) {
        this.imgTopAlbumsResource = imgTopAlbumsResource;
    }

    public String getTextItem0() {
        return textItem0;
    }

    public void setTextItem0(String textItem0) {
        this.textItem0 = textItem0;
    }

    public String getTextItem1() {
        return textItem1;
    }

    public void setTextItem1(String textItem1) {
        this.textItem1 = textItem1;
    }
}
