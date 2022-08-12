package com.tuanqd.mockproject.home.repository;

import com.tuanqd.mockproject.main.SongsModel;

import java.util.List;

public class AllSongsListRepository {
    private static AllSongsListRepository uniqueInstance;
    private List<SongsModel> mAllSongsList;
    private AllSongsListRepository() {

    }
    public static AllSongsListRepository getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new AllSongsListRepository();
        }
        return uniqueInstance;
    }

    public List<SongsModel> getAllSongsList() {
        return mAllSongsList;
    }

    public void setAllSongsList(List<SongsModel> mAllSongsList) {
        this.mAllSongsList = mAllSongsList;
    }
}
