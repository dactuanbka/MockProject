package com.tuanqd.mockproject.home;

import androidx.lifecycle.ViewModel;

import com.example.baseproject.R;

import java.util.ArrayList;
import java.util.List;

public class HomeViewModel extends ViewModel {
    private List<HomeModel> homeModelList;
    private List<RecommendedHomeModel> recommendedHomeModelList;
    private List<PlaylistHomeModel> playlistHomeModelList;
    private List<RecentlyPlayedHomeModel> recentlyPlayedHomeModelList ;

    private HomeModel homeModel;
    private PlaylistHomeModel playlistHomeModel;
    private RecommendedHomeModel recommendedHomeModel;
    private RecentlyPlayedHomeModel recentlyPlayedHomeModel;


    public void setData() {
        recommendedHomeModelList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            recommendedHomeModel = new RecommendedHomeModel(R.drawable.recommended,
                    "Sound of Sky", "Dilon Bruce");
            recommendedHomeModelList.add(recommendedHomeModel);
        }
        playlistHomeModelList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            playlistHomeModel = new PlaylistHomeModel(R.drawable.recommended,
                    "Classic Playlist", "Piano guys");
            playlistHomeModelList.add(playlistHomeModel);
        }
        homeModelList= new ArrayList<>();
        homeModel= new HomeModel(playlistHomeModelList,recommendedHomeModelList,null);
        homeModelList.add(homeModel);
    }

    public List<RecommendedHomeModel> getRecommendedHomeModelList() {
        return recommendedHomeModelList;
    }

    public List<HomeModel> getHomeModelList() {
        return homeModelList;
    }


    public List<RecentlyPlayedHomeModel> getRecentlyPlayedHomeModelList() {
        return recentlyPlayedHomeModelList;
    }


    public HomeModel getHomeModel() {
        return homeModel;
    }

    public PlaylistHomeModel getPlaylistHomeModel() {
        return playlistHomeModel;
    }


    public RecommendedHomeModel getRecommendedHomeModel() {
        return recommendedHomeModel;
    }


    public RecentlyPlayedHomeModel getRecentlyPlayedHomeModel() {
        return recentlyPlayedHomeModel;
    }

    public List<PlaylistHomeModel> getPlaylistHomeModelList() {
        return playlistHomeModelList;
    }
}
