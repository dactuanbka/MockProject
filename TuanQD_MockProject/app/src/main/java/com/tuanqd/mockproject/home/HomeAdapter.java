package com.tuanqd.mockproject.home;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.baseproject.R;
import com.example.baseproject.databinding.ItemHomeBinding;

import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.HomeViewHolder> {
    private List<HomeModel> homeModelList;
    private RecyclerView.RecycledViewPool recycledViewPool_recommended = new RecyclerView.RecycledViewPool();
    private RecyclerView.RecycledViewPool recycledViewPool_playlist = new RecyclerView.RecycledViewPool();
    private RecyclerView.RecycledViewPool recycledViewPool_recentlyPlayed = new RecyclerView.RecycledViewPool();


    public HomeAdapter(List<HomeModel> homeModelList) {
        this.homeModelList = homeModelList;
    }

    @NonNull
    @Override
    public HomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemHomeBinding itemHomeBinding = DataBindingUtil.inflate(inflater,
                R.layout.item_home, parent, false);
        return new HomeViewHolder(itemHomeBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeViewHolder holder, int position) {
        HomeModel homeModel = homeModelList.get(position);
        LinearLayoutManager layoutManager_playlist = new LinearLayoutManager(
                holder.itemHomeBinding.playlistRcvHome.getContext(),
                LinearLayoutManager.HORIZONTAL, false);

        LinearLayoutManager layoutManager_recommended = new LinearLayoutManager(
                holder.itemHomeBinding.recommendedRcvHome.getContext(),
                LinearLayoutManager.HORIZONTAL, false);

        LinearLayoutManager layoutManager_recentlyPlayed = new LinearLayoutManager(
                holder.itemHomeBinding.recentlyPlayedHome.getContext(),
                LinearLayoutManager.VERTICAL, false);
        /////////////
        RecommendedAdapter recommendedAdapter = new RecommendedAdapter(homeModel.
                getRecommendedHomeModelList());
        PlaylistHomeAdapter playlistHomeAdapter = new PlaylistHomeAdapter(homeModel.
                getPlaylistHomeModelList());
        RecentlyPlayedHomeAdapter recentlyPlayedHomeAdapter = new RecentlyPlayedHomeAdapter(
                homeModel.getRecentlyPlayedHomeModelList());
///////////////////
        holder.itemHomeBinding.recommendedRcvHome.setLayoutManager(layoutManager_recommended);
        holder.itemHomeBinding.recommendedRcvHome.setAdapter(recommendedAdapter);
        holder.itemHomeBinding.recommendedRcvHome.setHasFixedSize(true);
        holder.itemHomeBinding.recommendedRcvHome.setRecycledViewPool(recycledViewPool_recommended);
/////////////////////
        holder.itemHomeBinding.playlistRcvHome.setLayoutManager(layoutManager_playlist);
        holder.itemHomeBinding.playlistRcvHome.setAdapter(playlistHomeAdapter);
        holder.itemHomeBinding.recommendedRcvHome.setHasFixedSize(true);
        holder.itemHomeBinding.playlistRcvHome.setRecycledViewPool(recycledViewPool_playlist);
/////////////////////
        holder.itemHomeBinding.recentlyPlayedHome.setLayoutManager(layoutManager_recentlyPlayed);
        holder.itemHomeBinding.recentlyPlayedHome.setAdapter(recentlyPlayedHomeAdapter);
        holder.itemHomeBinding.recommendedRcvHome.setHasFixedSize(true);
        holder.itemHomeBinding.recentlyPlayedHome.setRecycledViewPool(recycledViewPool_recentlyPlayed);
    }


    @Override
    public int getItemCount() {
        if (homeModelList.size() != 0) {
            return homeModelList.size();
        }
        return 0;
    }

    public class HomeViewHolder extends RecyclerView.ViewHolder {
        private final ItemHomeBinding itemHomeBinding;

        public HomeViewHolder(@NonNull ItemHomeBinding itemHomeBinding) {
            super(itemHomeBinding.getRoot());
            this.itemHomeBinding = itemHomeBinding;
        }
    }
}
