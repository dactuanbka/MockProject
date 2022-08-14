package com.tuanqd.mockproject.songs.allsongs;


import android.app.Application;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.BindingAdapter;
import androidx.lifecycle.AndroidViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.CursorLoader;
import androidx.loader.content.Loader;

import com.tuanqd.mockproject.home.repository.AllSongsListRepository;
import com.tuanqd.mockproject.main.SongsModel;

import java.util.ArrayList;
import java.util.List;

public class AllSongsViewModel extends AndroidViewModel implements AllSongsAdapter.SongInAllSongsClicked {
    private MutableLiveData<Integer> positionAllSong = new MutableLiveData<Integer>();

    public LiveData<Integer> getPositionAllSong() {
        return positionAllSong;
    }

    private List<SongsModel> songsModelList = new ArrayList<>();
    AllSongsListRepository allSongsListRepository = AllSongsListRepository.getInstance();

    public AllSongsViewModel(@NonNull Application application) {
        super(application);
    }

    // set image for songs
    @BindingAdapter("bind:imageBitmap")
    public static void loadImage(ImageView img, Bitmap bitmap) {
        img.setImageBitmap(bitmap);
    }

    public List<SongsModel> getListData() {
        songsModelList = allSongsListRepository.getAllSongsList();
        return songsModelList;
    }


    @Override
    public void songOnClick(int position) {
     positionAllSong.setValue(position);
    }
}
