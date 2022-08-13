package com.tuanqd.mockproject.songs.artist.artistdetails;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.example.baseproject.R;

import java.util.ArrayList;
import java.util.List;

public class ArtistDetailViewModel extends ViewModel {
    List<ArtistDetailsModel> artistDetailsModelList = new ArrayList<>();
    List<TopAlbumsModel> topAlbumsModelList = new ArrayList<>();
    List<TopSongsModel> topSongsModelList = new ArrayList<>();

    public void setArtistDetailAdapter() {
        if (artistDetailsModelList != null) {
            artistDetailsModelList.clear();
        }
        artistDetailsModelList.add(new ArtistDetailsModel(ArtistDetailsModel.TOP_ALBUMS, "Top Albums",
                "View All", TopAlbumsAdap()));
        artistDetailsModelList.add(new ArtistDetailsModel(ArtistDetailsModel.TOP_SONGS, "Top Albums",
                "View All", TopSongsAdap()));
        setArtistDetailsModelList(artistDetailsModelList);
    }

    private TopSongsAdapter TopSongsAdap() {
        if(topSongsModelList!=null){
            topSongsModelList.clear();
        }
        topSongsModelList.add(new TopSongsModel(R.drawable.ic_play_music, "Second of You",
                "3:56", R.drawable.ic_more_top_songs_artist_details));
        topSongsModelList.add(new TopSongsModel(R.drawable.ic_play_music, "Whisper of Heart",
                "4:12", R.drawable.ic_more_top_songs_artist_details));
        topSongsModelList.add(new TopSongsModel(R.drawable.ic_play_music, "My Love",
                "3:54", R.drawable.ic_more_top_songs_artist_details));
        topSongsModelList.add(new TopSongsModel(R.drawable.ic_play_music, "Fade",
                "4:10", R.drawable.ic_more_top_songs_artist_details));
        return new TopSongsAdapter(topSongsModelList);
    }

    private TopAlbumsAdapter TopAlbumsAdap() {
        if(topAlbumsModelList!=null){
            topAlbumsModelList.clear();
        }
        topAlbumsModelList.add(new TopAlbumsModel(R.drawable.anh_dep_home, "Fire Dragon", "2019"));
        topAlbumsModelList.add(new TopAlbumsModel(R.drawable.anh_dep_home, "Sound of Life", "2018"));
        topAlbumsModelList.add(new TopAlbumsModel(R.drawable.anh_dep_home, "Giving Heart", "2017"));
        topAlbumsModelList.add(new TopAlbumsModel(R.drawable.anh_dep_home, "Dream", "2016"));
        return new TopAlbumsAdapter(topAlbumsModelList);
    }

    public List<ArtistDetailsModel> getArtistDetailsModelList() {
        return artistDetailsModelList;
    }

    public void setArtistDetailsModelList(List<ArtistDetailsModel> artistDetailsModelList) {
        this.artistDetailsModelList = artistDetailsModelList;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        Log.i("detail Artisr ViewModel onCleared ","");
    }
}
