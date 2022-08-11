package com.tuanqd.mockproject.home;

public class HomeModel {
    public static final int RECOMMENDED = 0;
    public static final int PLAYLIST = 1;
    public static final int RECENTLY_PLAYED = 2;
    private int viewType;
    private Object adapter;
    private String nameArea;
    private String viewAll;

    public HomeModel(int viewType, Object adapter, String nameArea, String viewAll) {
        this.viewType = viewType;
        this.adapter = adapter;
        this.nameArea = nameArea;
        this.viewAll = viewAll;
    }

    public String getNameArea() {
        return nameArea;
    }

    public void setNameArea(String nameArea) {
        this.nameArea = nameArea;
    }

    public String getViewAll() {
        return viewAll;
    }

    public void setViewAll(String viewAll) {
        this.viewAll = viewAll;
    }

    public int getViewType() {
        return viewType;
    }

    public void setViewType(int viewType) {
        this.viewType = viewType;
    }

    public Object getAdapter() {
        return adapter;
    }

    public void setAdapter(Object adapter) {
        this.adapter = adapter;
    }
}
