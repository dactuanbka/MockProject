package com.tuanqd.mockproject.songs.albums;

import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.baseproject.R;
import com.example.baseproject.databinding.ItemAlbumsFragmentBinding;

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.AlbumViewHolder> {
    Cursor albumCursor;
    Context mContext;
    ClickItemMenuAlbum clickItemMenuAlbum;
    PopupMenu popupMenuAlbum;

    public AlbumAdapter(Cursor albumCursor, Context mContext, ClickItemMenuAlbum clickItemMenuAlbum) {
        this.albumCursor = albumCursor;
        this.mContext = mContext;
        this.clickItemMenuAlbum = clickItemMenuAlbum;
    }


    @NonNull
    @Override
    public AlbumViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemAlbumsFragmentBinding mAlbumBinding = DataBindingUtil.inflate(inflater,
                R.layout.item_albums_fragment, parent, false);
        return new AlbumViewHolder(mAlbumBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull AlbumViewHolder holder, int position) {
        if (albumCursor != null && albumCursor.moveToPosition(position)) {
            String albumId = albumCursor.getString(albumCursor.getColumnIndexOrThrow(MediaStore.Audio.
                    Albums.ALBUM_ID));
            String Uri = "content://media/external/audio/albumart/" + albumId;
            String album = albumCursor.getString(albumCursor.getColumnIndexOrThrow(MediaStore.Audio.
                    Albums.ALBUM));
            String artist = albumCursor.getString(albumCursor.getColumnIndexOrThrow(MediaStore.Audio.
                    Albums.ARTIST));
            int numberSongs = albumCursor.getInt(albumCursor.getColumnIndexOrThrow(MediaStore.Audio.
                    Albums.NUMBER_OF_SONGS));
            AlbumModel albumModel = new AlbumModel(Uri, album, artist, numberSongs);

            holder.albumsFragmentBinding.setAlbumModel(albumModel);
            holder.albumsFragmentBinding.executePendingBindings();

            holder.albumsFragmentBinding.imgPopupmenuAlbum.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    popupMenuAlbum = new PopupMenu(mContext,
                            holder.albumsFragmentBinding.imgPopupmenuAlbum);
                    popupMenuAlbum.getMenuInflater().inflate(R.menu.menu_popup_artist_album, popupMenuAlbum.getMenu());
                    popupMenuAlbum.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            if (item.getItemId() == R.id.itemGoArtist) {
                                clickItemMenuAlbum.itemPopupAlbumClick(albumId);
                            }
                            return true;
                        }
                    });
                    popupMenuAlbum.show();
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return (albumCursor == null ? 0 : albumCursor.getCount());
    }

    public interface ClickItemMenuAlbum {
        void itemPopupAlbumClick(String albumId);
    }

    class AlbumViewHolder extends RecyclerView.ViewHolder {
        private ItemAlbumsFragmentBinding albumsFragmentBinding;

        public AlbumViewHolder(@NonNull ItemAlbumsFragmentBinding albumsFragmentBinding) {
            super(albumsFragmentBinding.getRoot());
            this.albumsFragmentBinding = albumsFragmentBinding;
        }
    }
}
