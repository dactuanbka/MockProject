package com.tuanqd.mockproject.main;

import android.app.Application;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaMetadataRetriever;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.AndroidViewModel;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.CursorLoader;
import androidx.loader.content.Loader;
import com.tuanqd.mockproject.home.repository.AllSongsListRepository;
import java.util.ArrayList;
import java.util.List;

public class BaseViewModel extends AndroidViewModel implements LoaderManager.LoaderCallbacks<Cursor> {
    Context mContext;
    int position = 0;
    private Cursor mCursor;
    private Bitmap bitmapSong;
    List<SongsModel> allSongsModelList = new ArrayList<>();
    AllSongsListRepository allSongsListRepository = AllSongsListRepository.getInstance();

    public BaseViewModel(@NonNull Application application) {
        super(application);
        this.mContext = application.getApplicationContext();
    }
// Query all Songs and information.

    public void setCursorData(Cursor mData) {
        this.mCursor = mData;
    }

    public Cursor getCursorData() {
        return mCursor;
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
                MediaStore.Audio.Media.ALBUM_ID,
                MediaStore.Audio.Media.ALBUM,
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
        // setCursor data
        setCursorData(data);
        Thread test = new Thread(new Runnable() {
            @Override
            public void run() {
                setRepository();
            }
        });
        test.start();
    }

    @Override
    public void onLoaderReset(@NonNull Loader<Cursor> loader) {
        // reset cursor
        setCursorData(null);
    }

    // make repository
    public void setRepository() {
        if (mCursor != null && mCursor.moveToPosition(position)) {
            String img = mCursor.getString(mCursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA));
            MediaMetadataRetriever metadataRetriever = new MediaMetadataRetriever();
            BitmapFactory.Options bfo = new BitmapFactory.Options();
            Log.i("TAG", "" + img);
            metadataRetriever.setDataSource(img);
            byte[] rawBitmap = metadataRetriever.getEmbeddedPicture();
            if (rawBitmap != null) {
                bitmapSong = BitmapFactory.decodeByteArray(rawBitmap, 0, rawBitmap.length, bfo);
            } else {
                bitmapSong = null;
            }
            // lấy ra String dữ liệu
            SongsModel allSongsModel = new SongsModel(
                    mCursor.getString(mCursor.getColumnIndexOrThrow(MediaStore.Audio.Media._ID)),
                    bitmapSong,
                    mCursor.getString(mCursor.getColumnIndexOrThrow(MediaStore.Audio.Media.TITLE)),
                    mCursor.getString(mCursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST)),
                    mCursor.getString(mCursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ALBUM_ID)),
                    mCursor.getString(mCursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ALBUM)),
                    mCursor.getString(mCursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DURATION)),
                    mCursor.getString(mCursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA)));
            // make singleTone list all_songs.
            allSongsModelList.add(allSongsModel);
            allSongsListRepository.setAllSongsList(allSongsModelList);
            Log.i("NAME ALBUMS", ""+ mCursor.getString(mCursor.
                    getColumnIndexOrThrow(MediaStore.Audio.Media.ALBUM))+" ID: \n"+mCursor.getString(mCursor.
                    getColumnIndexOrThrow(MediaStore.Audio.Media.ALBUM_ID)));
            position = position + 1;
            setRepository();
        }
    }
}
