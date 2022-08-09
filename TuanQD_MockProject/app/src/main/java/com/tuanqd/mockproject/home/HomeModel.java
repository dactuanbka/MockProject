package com.tuanqd.mockproject.home;

import java.util.List;

public class HomeModel {
    private List<PlaylistHomeModel> playlistHomeModelList;
    private List<RecommendedHomeModel> recommendedHomeModelList;
    private List<RecentlyPlayedHomeModel> recentlyPlayedHomeModelList;

    public HomeModel(List<PlaylistHomeModel> playlistHomeModelList,
                     List<RecommendedHomeModel> recommendedHomeModelList,
                     List<RecentlyPlayedHomeModel> recentlyPlayedHomeModelList) {
        this.playlistHomeModelList = playlistHomeModelList;
        this.recommendedHomeModelList = recommendedHomeModelList;
        this.recentlyPlayedHomeModelList = recentlyPlayedHomeModelList;
    }

    public List<PlaylistHomeModel> getPlaylistHomeModelList() {
        return playlistHomeModelList;
    }

    public void setPlaylistHomeModelList(List<PlaylistHomeModel> playlistHomeModelList) {
        this.playlistHomeModelList = playlistHomeModelList;
    }

    public List<RecommendedHomeModel> getRecommendedHomeModelList() {
        return recommendedHomeModelList;
    }

    public void setRecommendedHomeModelList(List<RecommendedHomeModel> recommendedHomeModelList) {
        this.recommendedHomeModelList = recommendedHomeModelList;
    }

    public List<RecentlyPlayedHomeModel> getRecentlyPlayedHomeModelList() {
        return recentlyPlayedHomeModelList;
    }

    public void setRecentlyPlayedHomeModelList(List<RecentlyPlayedHomeModel> recentlyPlayedHomeModelList) {
        this.recentlyPlayedHomeModelList = recentlyPlayedHomeModelList;
    }
}
