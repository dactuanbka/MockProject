package com.tuanqd.mockproject.songs.viewpager_songs;

import android.database.Cursor;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.baseproject.R;
import com.example.baseproject.databinding.ItemAllsongsSongsBinding;

public class AllSongsAdapter extends RecyclerView.Adapter<AllSongsAdapter.AllSongsViewHolder> {
    private Cursor deviceCursor;
//    int count = 0;

    public AllSongsAdapter(Cursor deviceCursor) {
        this.deviceCursor = deviceCursor;
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
            AllSongsModel allSongsModel = new AllSongsModel(
                    deviceCursor.getString(deviceCursor.getColumnIndexOrThrow(MediaStore.Audio.Media._ID)),
                    R.drawable.ic_burger_menu,
                    deviceCursor.getString(deviceCursor.getColumnIndexOrThrow(MediaStore.Audio.Media.TITLE)),
                    deviceCursor.getString(deviceCursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST)),
                    deviceCursor.getString(deviceCursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DURATION)));
            holder.mItemAllSongsSongsBinding.setAllSongsModel(allSongsModel);
            holder.mItemAllSongsSongsBinding.executePendingBindings();

        }
    }

    @Override
    public int getItemCount() {
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

        }
    }
}


