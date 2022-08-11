package com.tuanqd.mockproject.songs.artist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.baseproject.R;
import com.example.baseproject.databinding.ItemTopAlbumsArtistDetailsBinding;

import java.util.List;

public class TopAlbumsAdapter extends RecyclerView.Adapter<TopAlbumsAdapter.TopAlbumsViewHolder> {
    List<TopAlbumsModel> topAlbumsModelList;

    public TopAlbumsAdapter(List<TopAlbumsModel> topAlbumsModelList) {
        this.topAlbumsModelList = topAlbumsModelList;
    }

    @NonNull
    @Override
    public TopAlbumsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemTopAlbumsArtistDetailsBinding topAlbumsArtistDetailsBinding = DataBindingUtil.inflate(inflater,
                R.layout.item_top_albums_artist_details, parent, false);

        return new TopAlbumsViewHolder(topAlbumsArtistDetailsBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull TopAlbumsViewHolder holder, int position) {
        holder.binding.setTopAlbumsModel(topAlbumsModelList.get(position));
        holder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        if (topAlbumsModelList != null) {
            return topAlbumsModelList.size();
        } else {
            return 0;
        }
    }

    class TopAlbumsViewHolder extends RecyclerView.ViewHolder {
        private ItemTopAlbumsArtistDetailsBinding binding;

        public TopAlbumsViewHolder(@NonNull ItemTopAlbumsArtistDetailsBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
