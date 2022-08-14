package com.tuanqd.mockproject.songs.artist;

import android.app.Application;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
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

import java.util.ArrayList;
import java.util.List;

public class ArtistViewModel extends AndroidViewModel implements LoaderManager.LoaderCallbacks<Cursor> {
    private List<ArtistModel> artistModeTemplList = new ArrayList<>();
    Cursor artistCursor;
    Context mContext;
    int position = 0;
    private MutableLiveData<Boolean> finishLoaderArtist = new MutableLiveData<>();
    public LiveData<Boolean> getFinishLoaderArtist(){
        return finishLoaderArtist;
    }

    public ArtistViewModel(@NonNull Application application) {
        super(application);
        mContext = application.getApplicationContext();
    }

    public Cursor getArtistCursor() {
        return artistCursor;
    }

    public void setArtistCursor(Cursor artistCursor) {
        this.artistCursor = artistCursor;
    }

    @BindingAdapter("bindArtist:imageBitmap")
    public static void loadImageArtist(ImageView img, Bitmap bitmap) {
        img.setImageBitmap(bitmap);
    }
    @NonNull
    @Override
    public Loader<Cursor> onCreateLoader(int id, @Nullable Bundle args) {
        Loader<Cursor> loader;
        String[] projection = {
                MediaStore.Audio.Artists._ID,
                MediaStore.Audio.Artists.ARTIST,     // name artist
                MediaStore.Audio.Artists.NUMBER_OF_ALBUMS,
                MediaStore.Audio.Artists.NUMBER_OF_TRACKS
        };
        loader = new CursorLoader(mContext,
                MediaStore.Audio.Artists.EXTERNAL_CONTENT_URI,
                projection,
                null,
                null, null);
        return loader;
    }
    @Override
    public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor data) {
        setArtistCursor(data);
        finishLoaderArtist.setValue(true);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<Cursor> loader) {
        setArtistCursor(null);
    }

}
