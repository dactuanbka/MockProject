package com.tuanqd.mockproject.songs.allsongs;

import java.util.List;

public class AllSongsListRepository {
    private static AllSongsListRepository uniqueInstance;
    private List<AllSongsModel> mAllSongsList;
    private AllSongsListRepository() {

    }
    public static AllSongsListRepository getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new AllSongsListRepository();
        }
        return uniqueInstance;
    }

    public List<AllSongsModel> getAllSongsList() {
        return mAllSongsList;
    }

    public void setAllSongsList(List<AllSongsModel> mAllSongsList) {
        this.mAllSongsList = mAllSongsList;
    }
}
