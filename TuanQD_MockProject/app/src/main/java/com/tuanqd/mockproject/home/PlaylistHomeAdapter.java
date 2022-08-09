package com.tuanqd.mockproject.home;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.baseproject.R;
import com.example.baseproject.databinding.ItemPlaylistHomeBinding;

import java.util.List;

public class PlaylistHomeAdapter extends RecyclerView.Adapter<PlaylistHomeAdapter.PlaylistViewHolder> {
    List<PlaylistHomeModel> playlistHomeModelList;

    public PlaylistHomeAdapter(List<PlaylistHomeModel> playlistHomeModelList) {
        this.playlistHomeModelList = playlistHomeModelList;
    }

    @NonNull
    @Override
    public PlaylistViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(parent.getContext());
        ItemPlaylistHomeBinding itemPlaylistHomeBinding= DataBindingUtil.inflate(inflater,
                R.layout.item_playlist_home,parent, false);
        return new PlaylistViewHolder(itemPlaylistHomeBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull PlaylistViewHolder holder, int position) {
        holder.playlistHomeBinding.setPlaylistModel(playlistHomeModelList.get(position));
        holder.playlistHomeBinding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        if (playlistHomeModelList != null) {
            return playlistHomeModelList.size();
        } else {
            return 0;
        }
    }

    public class PlaylistViewHolder extends RecyclerView.ViewHolder {
        private final ItemPlaylistHomeBinding playlistHomeBinding;

        public PlaylistViewHolder(ItemPlaylistHomeBinding playlistHomeBinding) {
            super(playlistHomeBinding.getRoot());
            this.playlistHomeBinding = playlistHomeBinding;
        }
    }
}
