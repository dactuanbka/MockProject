package com.tuanqd.mockproject.songs.artist.artistdetails;

public class ArtistDetailsModel {
    private int viewType;
    private String titleArtistDetail;
    private String viewAllArtistDetail;
    private Object adapter;
    public static final int TOP_ALBUMS = 0;
    public static final int TOP_SONGS = 1;

    public ArtistDetailsModel(int viewType,String titleArtistDetail, String viewAllArtistDetail, Object adapter ) {
        this.viewType=viewType;
        this.titleArtistDetail = titleArtistDetail;
        this.viewAllArtistDetail = viewAllArtistDetail;
        this.adapter = adapter;

    }

    public int getViewType() {
        return viewType;
    }

    public void setViewType(int viewType) {
        this.viewType = viewType;
    }

    public String getTitleArtistDetail() {
        return titleArtistDetail;
    }

    public void setTitleArtistDetail(String titleArtistDetail) {
        this.titleArtistDetail = titleArtistDetail;
    }

    public String getViewAllArtistDetail() {
        return viewAllArtistDetail;
    }

    public void setViewAllArtistDetail(String viewAllArtistDetail) {
        this.viewAllArtistDetail = viewAllArtistDetail;
    }

    public Object getAdapter() {
        return adapter;
    }

    public void setAdapter(Object adapter) {
        this.adapter = adapter;
    }
}
