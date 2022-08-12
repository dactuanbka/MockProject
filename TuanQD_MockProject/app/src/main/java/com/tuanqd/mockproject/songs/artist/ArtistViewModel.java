package com.tuanqd.mockproject.songs.artist;

import android.database.Cursor;
import android.graphics.Bitmap;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;
public class ArtistViewModel {
    private Cursor mData;
    @BindingAdapter("bind:imageBitmap")
    public static void loadImageArtist(ImageView img, Bitmap bitmap) {
        img.setImageBitmap(bitmap);
    }

    public void setCursorDataArtist(Cursor mData) {
        this.mData = mData;
    }

    public Cursor getCursorDataArtist() {
        return mData;
    }
}
