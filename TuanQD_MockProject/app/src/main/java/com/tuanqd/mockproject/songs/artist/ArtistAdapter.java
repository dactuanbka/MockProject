package com.tuanqd.mockproject.songs.artist;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
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
import com.example.baseproject.databinding.ItemArtistFragmentBinding;
import com.tuanqd.mockproject.home.repository.AllSongsListRepository;

public class ArtistAdapter extends RecyclerView.Adapter<ArtistAdapter.ArtistViewHolder> {
    //    List<ArtistModel> artistModelList;
    AllSongsListRepository allSongsListRepository = AllSongsListRepository.getInstance();
    Cursor artistCursor;
    Context mContext;
    PopupMenu popupMenu;
    ClickItemMenu clickItemMenu;
    Bitmap artistBitmap = null;

    public ArtistAdapter(Cursor artistCursor, Context mContext, ClickItemMenu clickItemMenu) {
        this.artistCursor = artistCursor;
        this.mContext = mContext;
        this.clickItemMenu = clickItemMenu;
    }

    @NonNull
    @Override
    public ArtistViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemArtistFragmentBinding mArtistBinding = DataBindingUtil.inflate(inflater,
                R.layout.item_artist_fragment, parent, false);
        return new ArtistViewHolder(mArtistBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ArtistViewHolder holder, int position) {

        if (artistCursor != null && artistCursor.moveToPosition(position)) {
            String artist = artistCursor.getString(artistCursor.getColumnIndexOrThrow(MediaStore.Audio.
                    Artists.ARTIST));
            int numberAlbum = artistCursor.getInt(artistCursor.getColumnIndexOrThrow(MediaStore.Audio.
                    Artists.NUMBER_OF_ALBUMS));
            int numberSongs = artistCursor.getInt(artistCursor.getColumnIndexOrThrow(MediaStore.Audio.
                    Artists.NUMBER_OF_TRACKS));
            ArtistModel artistModel = new ArtistModel(getArtistBitmap(artistCursor), artist, numberAlbum, numberSongs);
            holder.artistFragmentBinding.setArtistModel(artistModel);
            holder.artistFragmentBinding.executePendingBindings();

        holder.artistFragmentBinding.imgPopupMenuArtist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupMenu = new PopupMenu(mContext,
                        holder.artistFragmentBinding.imgPopupMenuArtist);
                popupMenu.getMenuInflater().inflate(R.menu.menu_popup_artist_album, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        if (item.getItemId() == R.id.itemGoArtist) {
                            clickItemMenu.itemPopupClick();
                        }
                        return true;
                    }
                });
                popupMenu.show();
            }
        });
    }
    }

    @Override
    public int getItemCount() {

        return (artistCursor == null ? 0 : artistCursor.getCount());
    }

    public void swapCursor(Cursor newDeviceCursor) {
        if (artistCursor != null) {
            artistCursor.close();
            artistCursor = newDeviceCursor;
            notifyDataSetChanged();
        }
    }

    public Bitmap getArtistBitmap(Cursor cursor) {
        for (int j = 0; j < allSongsListRepository.getAllSongsList().size(); j++) {
            String artist = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Artists.ARTIST));
            if (artist.equals(allSongsListRepository.getAllSongsList().get(j).getArtist())) {
                artistBitmap = allSongsListRepository.getAllSongsList().get(j).getSongBitmap();
            }
        }
        return artistBitmap;
    }

    public interface ClickItemMenu {
        void itemPopupClick();
    }

    class ArtistViewHolder extends RecyclerView.ViewHolder {
        private final ItemArtistFragmentBinding artistFragmentBinding;

        public ArtistViewHolder(@NonNull ItemArtistFragmentBinding artistFragmentBinding) {
            super(artistFragmentBinding.getRoot());
            this.artistFragmentBinding = artistFragmentBinding;
        }
    }
}
