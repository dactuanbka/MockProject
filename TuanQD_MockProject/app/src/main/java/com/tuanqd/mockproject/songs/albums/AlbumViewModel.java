package com.tuanqd.mockproject.songs.albums;

import android.app.Application;
import android.content.Context;
import android.database.Cursor;
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

import com.bumptech.glide.Glide;
import com.example.baseproject.R;

public class AlbumViewModel extends AndroidViewModel implements LoaderManager.LoaderCallbacks<Cursor> {
    Context mContext;
    Cursor albumCursor;
    String positionAlbumDetail;

    public String getPositionAlbumDetail() {
        return positionAlbumDetail;
    }

    public void setPositionAlbumDetail(String positionAlbumDetail) {
        this.positionAlbumDetail = positionAlbumDetail;
    }

    public AlbumViewModel(@NonNull Application application) {
        super(application);
        this.mContext = application.getApplicationContext();
    }

    private MutableLiveData<Boolean> finishLoaderAlbum = new MutableLiveData<>();

    public LiveData<Boolean> getFinishLoaderAlbum() {
        return finishLoaderAlbum;
    }

    @BindingAdapter("bindAlbum:imageUrl")
    public static void setImageUrl(ImageView imageView, String url) {
        Glide.with(imageView.getContext())
                .load(url)
                .placeholder(R.drawable.loading_img)
                .error(R.drawable.thumbai_zing)
                .into(imageView);
    }

    public Cursor getAlbumCursor() {
        return albumCursor;
    }

    public void setAlbumCursor(Cursor albumCursor) {
        this.albumCursor = albumCursor;
    }

    @NonNull
    @Override
    public Loader<Cursor> onCreateLoader(int id, @Nullable Bundle args) {
        Loader<Cursor> loader;
        String[] projection = {
                MediaStore.Audio.Albums.ALBUM_ID,
                MediaStore.Audio.Albums.ALBUM,     // name artist
                MediaStore.Audio.Albums.ARTIST,
                MediaStore.Audio.Albums.NUMBER_OF_SONGS
        };
        loader = new CursorLoader(mContext,
                MediaStore.Audio.Albums.EXTERNAL_CONTENT_URI,
                projection,
                null,
                null, null);
        return loader;
    }

    @Override
    public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor data) {
        setAlbumCursor(data);
        finishLoaderAlbum.setValue(true);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<Cursor> loader) {
        setAlbumCursor(null);
    }
}
