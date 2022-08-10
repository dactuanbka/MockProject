package com.tuanqd.mockproject.songs.allsongs;

import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaMetadataRetriever;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.baseproject.R;
import com.example.baseproject.databinding.ItemAllsongsSongsBinding;

import java.util.ArrayList;
import java.util.List;

public class AllSongsAdapter extends RecyclerView.Adapter<AllSongsAdapter.AllSongsViewHolder> {
    private Cursor deviceCursor;
    Bitmap bitmap = null;
    int count = 0;
    AllSongsListRepository allSongsListRepository = AllSongsListRepository.getInstance();
    SongInAllSongsClicked songInAllSongClicked;
    List<AllSongsModel> allSongsModelList = new ArrayList<>();

    public AllSongsAdapter(Cursor deviceCursor, SongInAllSongsClicked songInAllSongClicked) {
        this.deviceCursor = deviceCursor;
        this.songInAllSongClicked = songInAllSongClicked;
        setHasStableIds(true);
    }

    @NonNull
    @Override
    public AllSongsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemAllsongsSongsBinding binding = DataBindingUtil.inflate(inflater, R.layout.item_allsongs_songs,
                parent, false);
        return new AllSongsViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull AllSongsViewHolder holder, int position) {
        if (deviceCursor != null && deviceCursor.moveToPosition(position)) {
            String img = deviceCursor.getString(deviceCursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA));
            MediaMetadataRetriever metadataRetriever = new MediaMetadataRetriever();
            BitmapFactory.Options bfo = new BitmapFactory.Options();
            Log.i("TAG", "" + img);
            metadataRetriever.setDataSource(img);
            byte[] rawBitmap = metadataRetriever.getEmbeddedPicture();
            if (rawBitmap != null) {
                bitmap = BitmapFactory.decodeByteArray(rawBitmap, 0, rawBitmap.length, bfo);
            }
            AllSongsModel allSongsModel = new AllSongsModel(
                    deviceCursor.getString(deviceCursor.getColumnIndexOrThrow(MediaStore.Audio.Media._ID)),
                    bitmap,
                    deviceCursor.getString(deviceCursor.getColumnIndexOrThrow(MediaStore.Audio.Media.TITLE)),
                    deviceCursor.getString(deviceCursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST)),
                    deviceCursor.getString(deviceCursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DURATION)),
                    deviceCursor.getString(deviceCursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA)));
            // make singleTone list all_songs.
            allSongsModelList.add(allSongsModel);
            allSongsListRepository.setAllSongsList(allSongsModelList);

            /////
            holder.mItemAllSongsSongsBinding.setAllSongsModel(allSongsModel);
            holder.mItemAllSongsSongsBinding.executePendingBindings();
        }
    }

    @Override
    public int getItemCount() {
        Log.i("COUNT", "" + count);
        if (deviceCursor != null) {
            return deviceCursor.getCount();
        } else {
            return 0;
        }
    }

    public void swapCursor(Cursor newDeviceCursor) {
        if (deviceCursor != null) {
            deviceCursor.close();
            deviceCursor = newDeviceCursor;
            notifyDataSetChanged();
        }
    }

    public class AllSongsViewHolder extends RecyclerView.ViewHolder {
        private ItemAllsongsSongsBinding mItemAllSongsSongsBinding;

        public AllSongsViewHolder(@NonNull ItemAllsongsSongsBinding itemAllsongsSongsBinding) {

            super(itemAllsongsSongsBinding.getRoot());
            this.mItemAllSongsSongsBinding = itemAllsongsSongsBinding;
            mItemAllSongsSongsBinding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    songInAllSongClicked.songOnClick(getBindingAdapterPosition());
                }
            });
        }
    }

    public interface SongInAllSongsClicked {
        void songOnClick(int position);
    }
}


