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

import androidx.loader.app.LoaderManager;
import androidx.loader.content.CursorLoader;
import androidx.loader.content.Loader;

public class AllSongsViewModel extends AndroidViewModel implements LoaderManager.LoaderCallbacks<Cursor> {
    Context mContext;
    private Cursor mData;

    public AllSongsViewModel(@NonNull Application application) {
        super(application);
        mContext = application.getApplicationContext();
    }

    // set image for songs
    @BindingAdapter("bind:imageBitmap")
    public static void loadImage(ImageView img, Bitmap bitmap) {
        img.setImageBitmap(bitmap);
    }

    public void setCursorData(Cursor mData) {
        this.mData = mData;
    }

    public Cursor getCursorData() {
        return mData;
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
                MediaStore.Audio.Media.IS_MUSIC + "!=0",
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
