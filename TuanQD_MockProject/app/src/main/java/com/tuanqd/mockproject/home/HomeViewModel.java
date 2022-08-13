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
                "Hot Recommended", "");
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
        recommendedHomeModelList.add(new RecommendedHomeModel(R.drawable.thumbail, "Sound of Sky", "Dilon Bruce"));
        recommendedHomeModelList.add(new RecommendedHomeModel(R.drawable.thumbail, "Sound of Sky", "Dilon Bruce"));
        recommendedHomeModelList.add(new RecommendedHomeModel(R.drawable.thumbail, "Sound of Sky", "Dilon Bruce"));
        recommendedHomeModelList.add(new RecommendedHomeModel(R.drawable.thumbail, "Sound of Sky", "Dilon Bruce"));
        return new RecommendedAdapter(recommendedHomeModelList);
    }

    private RecentlyPlayedHomeAdapter RecentlyPlayedAdapter() {
        recentlyPlayedHomeModelList = new ArrayList<>();

            recentlyPlayedHomeModelList.add(new RecentlyPlayedHomeModel(R.drawable.ic_play_music,
                    "Earth Song", "Michael Jackson", R.drawable.ic_heart, R.drawable.ic_group_rate));
        recentlyPlayedHomeModelList.add(new RecentlyPlayedHomeModel(R.drawable.ic_play_music,
                "My Love", "WestLife", R.drawable.ic_heart, R.drawable.ic_group_rate));
        recentlyPlayedHomeModelList.add(new RecentlyPlayedHomeModel(R.drawable.ic_play_music,
                "Unstoppable", "Sia", R.drawable.ic_heart, R.drawable.ic_group_rate));
        recentlyPlayedHomeModelList.add(new RecentlyPlayedHomeModel(R.drawable.ic_play_music,
                "Faded", "Alan Walker", R.drawable.ic_heart, R.drawable.ic_group_rate));
        recentlyPlayedHomeModelList.add(new RecentlyPlayedHomeModel(R.drawable.ic_play_music,
                "Heal the World", "Michael Jackson", R.drawable.ic_heart, R.drawable.ic_group_rate));
        return new RecentlyPlayedHomeAdapter(recentlyPlayedHomeModelList);
    }

    private PlaylistHomeAdapter PlaylistAdapter() {
        playlistHomeModelList = new ArrayList<>();

        playlistHomeModelList.add(new PlaylistHomeModel(R.drawable.album_zing2jpg, "Classic Playlist", "piano"));
        playlistHomeModelList.add(new PlaylistHomeModel(R.drawable.classic_music, "Summer Playlist", "Dilon Bruce"));
        playlistHomeModelList.add(new PlaylistHomeModel(R.drawable.classic_music2, "Pop Music", "Michael Jackson"));
        playlistHomeModelList.add(new PlaylistHomeModel(R.drawable.album_zing, "Faded Playlist", "Alan Walker"));
        playlistHomeModelList.add(new PlaylistHomeModel(R.drawable.thumbai_zing, "Love Playlist", "WestLife"));
        return new PlaylistHomeAdapter(playlistHomeModelList);
    }

    public List<HomeModel> getHomeModelList() {
        return homeModelList;
    }

    public HomeModel getHomeModel() {
        return homeModel;
    }

}
