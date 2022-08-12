package com.tuanqd.mockproject.home;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModel;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.CursorLoader;
import androidx.loader.content.Loader;

import com.example.baseproject.R;

import java.util.ArrayList;
import java.util.List;

public class HomeViewModel extends ViewModel {
    private List<HomeModel> homeModelList;
    private List<RecommendedHomeModel> recommendedHomeModelList;
    private List<PlaylistHomeModel> playlistHomeModelList;
    private List<RecentlyPlayedHomeModel> recentlyPlayedHomeModelList;
    private HomeModel homeModel;
    Context mContext;
    private Cursor mData;

    public void setData() {
        // set for Recommended list
        homeModelList = new ArrayList<>();
        homeModel = new HomeModel(HomeModel.RECOMMENDED, RecommendedAdapter(),
                "Hot Recommended",  "");
        homeModelList.add(homeModel);
        // set for Playlist list

        homeModel = new HomeModel(HomeModel.PLAYLIST, PlaylistAdapter(),
                "Playlist", "ViewAll");
        homeModelList.add(homeModel);

        // set for recently playlist
        homeModel = new HomeModel(HomeModel.RECENTLY_PLAYED, RecentlyPlayedAdapter(),
                "Recently Played", "ViewAll");
        homeModelList.add(homeModel);
    }

    private RecommendedAdapter RecommendedAdapter() {
        recommendedHomeModelList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            recommendedHomeModelList.add(new RecommendedHomeModel(R.drawable.anh_dep_home, "Sound of Sky", "Dilon Bruce"));
        }
        return new RecommendedAdapter(recommendedHomeModelList);
    }

    private RecentlyPlayedHomeAdapter RecentlyPlayedAdapter() {
        recentlyPlayedHomeModelList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            recentlyPlayedHomeModelList.add(new RecentlyPlayedHomeModel(R.drawable.ic_play_music,
                    "Earth Song", "Michael Jackson",R.drawable.ic_heart,R.drawable.ic_group_rate ));
        }
        return new RecentlyPlayedHomeAdapter(recentlyPlayedHomeModelList);
    }

    private PlaylistHomeAdapter PlaylistAdapter() {
        playlistHomeModelList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            playlistHomeModelList.add(new PlaylistHomeModel(R.drawable.anh_dep_home, "Classic Playlist", "piano"));
        }
        return new PlaylistHomeAdapter(playlistHomeModelList);
    }

    public List<HomeModel> getHomeModelList() {
        return homeModelList;
    }

    public HomeModel getHomeModel() {
        return homeModel;
    }

}
