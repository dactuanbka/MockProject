package com.tuanqd.mockproject.songs.viewpager_songs;

import android.Manifest;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.CursorLoader;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class AllSongsViewModel extends AndroidViewModel implements LoaderManager.LoaderCallbacks<Cursor> {
    Context mContext;
    private Cursor mData;
    private static final int LOADER_DEVICE_ID = 1;
    public AllSongsViewModel(@NonNull Application application) {
        super(application);
        this.mContext = application.getApplicationContext();
        initLoader();
    }
    public void setCursorData(Cursor mData) {
        this.mData = mData;
    }
    public Cursor getCursorData() {
        return mData;
    }
    private void initLoader() {
        if(ContextCompat.checkSelfPermission(mContext, Manifest.permission.READ_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_GRANTED){
        LoaderManager.getInstance(getApplication()).initLoader(LOADER_DEVICE_ID,null,this);
        }
    }

    @NonNull
    @Override
    public Loader<Cursor> onCreateLoader(int id, @Nullable Bundle args) {

        Loader<Cursor> loader;
        String[] projection = {
                MediaStore.Audio.Media._ID,
                MediaStore.Audio.Media.TITLE,
                MediaStore.Audio.Media.ARTIST,
                MediaStore.Audio.Media.DATA,
                MediaStore.Audio.Media.DURATION
        };
        loader = new CursorLoader(mContext,
                MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                projection,
                null,
                null, null);
        return loader;
    }

    @Override
    public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor data) {
        if (data != null) {
            // listen data
            setCursorData(data);
        }
    }

    @Override
    public void onLoaderReset(@NonNull Loader<Cursor> loader) {
       // listen cursor
        setCursorData(null);
    }

}
