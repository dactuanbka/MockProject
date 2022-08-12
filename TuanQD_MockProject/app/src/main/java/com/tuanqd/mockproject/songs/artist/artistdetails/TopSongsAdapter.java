package com.tuanqd.mockproject.songs.artist.artistdetails;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.baseproject.R;
import com.example.baseproject.databinding.ItemTopsongsArtistDetailsBinding;

import java.util.List;

public class TopSongsAdapter extends RecyclerView.Adapter<TopSongsAdapter.TopSongViewHolder> {
    List<TopSongsModel> topSongsModelList;

    public TopSongsAdapter(List<TopSongsModel> topSongsModelList) {
        this.topSongsModelList = topSongsModelList;
    }

    @NonNull
    @Override
    public TopSongViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemTopsongsArtistDetailsBinding itemTopsongsArtistDetailsBinding = DataBindingUtil.inflate(inflater,
                R.layout.item_topsongs_artist_details, parent, false);
        return new TopSongViewHolder(itemTopsongsArtistDetailsBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull TopSongViewHolder holder, int position) {
        holder.binding.setTopSongsModel(topSongsModelList.get(position));
        holder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        if (topSongsModelList != null) {
            return topSongsModelList.size();
        } else {
            return 0;
        }
    }

    class TopSongViewHolder extends RecyclerView.ViewHolder {
        ItemTopsongsArtistDetailsBinding binding;

        public TopSongViewHolder(@NonNull ItemTopsongsArtistDetailsBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
