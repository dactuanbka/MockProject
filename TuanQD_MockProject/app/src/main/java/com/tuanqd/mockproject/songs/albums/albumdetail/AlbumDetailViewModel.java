package com.tuanqd.mockproject.songs.albums.albumdetail;

import android.app.Application;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.CursorLoader;
import androidx.loader.content.Loader;

import com.example.baseproject.R;
import com.tuanqd.mockproject.home.repository.AllSongsListRepository;
import com.tuanqd.mockproject.main.SongsModel;

import java.util.ArrayList;
import java.util.List;

public class AlbumDetailViewModel extends ViewModel {
    AllSongsListRepository allSongsListRepository = AllSongsListRepository.getInstance();
    List<SongsModel> songsModelList = allSongsListRepository.getAllSongsList();
    List<AlbumDetailModel> albumDetailModelList = new ArrayList<>();


    private MutableLiveData<Boolean> finishLoaderAlbumDetail = new MutableLiveData<>();

    public LiveData<Boolean> getFinishLoaderAlbumDetail() {
        return finishLoaderAlbumDetail;
    }

    public void setFinishLoaderAlbumDetail(MutableLiveData<Boolean> finishLoaderAlbumDetail) {
        this.finishLoaderAlbumDetail = finishLoaderAlbumDetail;
    }

    public List<AlbumDetailModel> initAlbumDetailList(String albumId) {
        if (albumDetailModelList != null) {
            albumDetailModelList.clear();
        }
        for (int i = 0; i < songsModelList.size(); i++) {
            if ((songsModelList.get(i).getAlbumId()).equals(albumId)) {
                AlbumDetailModel albumDetailModel = new AlbumDetailModel(
                        R.drawable.ic_play_music, songsModelList.get(i).getTitle(),
                        songsModelList.get(i).getDuration(),
                        R.drawable.ic_more_top_songs_artist_details);
                albumDetailModelList.add(albumDetailModel);
            }
        }
        return albumDetailModelList;
    }

}
