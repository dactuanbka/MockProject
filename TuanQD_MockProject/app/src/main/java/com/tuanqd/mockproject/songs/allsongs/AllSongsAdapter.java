package com.tuanqd.mockproject.songs.allsongs;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.baseproject.R;
import com.example.baseproject.databinding.ItemAllsongsSongsBinding;
import com.tuanqd.mockproject.home.repository.AllSongsListRepository;
import com.tuanqd.mockproject.main.SongsModel;

import java.util.List;

public class AllSongsAdapter extends RecyclerView.Adapter<AllSongsAdapter.AllSongsViewHolder> {
    AllSongsListRepository allSongsListRepository = AllSongsListRepository.getInstance();
    SongInAllSongsClicked songInAllSongClicked;
    List<SongsModel> allSongsModelList;

    public AllSongsAdapter(SongInAllSongsClicked songInAllSongClicked, List<SongsModel> allSongsModelList) {
        this.songInAllSongClicked = songInAllSongClicked;
        this.allSongsModelList = allSongsModelList;
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
        holder.allSongBinding.setAllSongsModel(allSongsModelList.get(position));
        holder.allSongBinding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        if (allSongsModelList != null) {
            return allSongsModelList.size();
        } else {
            return 0;
        }
    }
    public class AllSongsViewHolder extends RecyclerView.ViewHolder {
        private ItemAllsongsSongsBinding allSongBinding;

        public AllSongsViewHolder(@NonNull ItemAllsongsSongsBinding allSongBinding) {

            super(allSongBinding.getRoot());
            this.allSongBinding = allSongBinding;
            allSongBinding.getRoot().setOnClickListener(new View.OnClickListener() {
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


