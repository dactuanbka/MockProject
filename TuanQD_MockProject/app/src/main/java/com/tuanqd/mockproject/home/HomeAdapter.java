package com.tuanqd.mockproject.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.baseproject.R;
import com.example.baseproject.databinding.FragmentHomeBinding;
import com.example.baseproject.databinding.ItemPlaylistHomeBinding;
import com.example.baseproject.databinding.ItemRecentlyPlayedHomeBinding;
import com.example.baseproject.databinding.ItemRecommendHomeBinding;
import com.example.baseproject.databinding.RecyclerviewFragmentHomeBinding;

import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.HomeViewHolder> {
    private List<HomeModel> homeModelList;
    Context context;
    private static final int RECOMMENDED = 0;
    private static final int PLAYLIST = 1;
    private static final int RECENTLY_PLAYED = 2;


    public HomeAdapter(List<HomeModel> homeModelList, Context context) {
        this.homeModelList = homeModelList;
        this.context = context;
    }
    @NonNull
    @Override
    public HomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        RecyclerviewFragmentHomeBinding homeBinding = DataBindingUtil.inflate(
                inflater, R.layout.recyclerview_fragment_home, parent, false);
        return new HomeViewHolder(homeBinding);
    }
    @Override
    public void onBindViewHolder(@NonNull HomeViewHolder holder, int position) {
        HomeModel homeModel = homeModelList.get(position);
        holder.homeBinding.recyclerViewHomeFragment.setAdapter((RecyclerView.Adapter) homeModel.getAdapter());
        if (holder.getItemViewType() == RECOMMENDED || holder.getItemViewType() == PLAYLIST) {
            holder.homeBinding.recyclerViewHomeFragment.setLayoutManager(new LinearLayoutManager(context,
                    RecyclerView.HORIZONTAL, false));
        } else {
            holder.homeBinding.recyclerViewHomeFragment.setLayoutManager(new LinearLayoutManager(context,
                    RecyclerView.VERTICAL, false));
        }
        holder.homeBinding.setHomeModel(homeModel);
        holder.homeBinding.executePendingBindings();
    }
    @Override
    public int getItemViewType(int position) {
        if (homeModelList.get(position).getViewType() == 0) {
            return RECOMMENDED;
        } else {
            if (homeModelList.get(position).getViewType() == 1) {
                return PLAYLIST;
            } else {
                return RECENTLY_PLAYED;
            }
        }
    }

    @Override
    public int getItemCount() {
        if (homeModelList.size() != 0) {
            return homeModelList.size();
        }
        return 0;
    }

    public static class HomeViewHolder extends RecyclerView.ViewHolder {
        private final RecyclerviewFragmentHomeBinding homeBinding;

        public HomeViewHolder(@NonNull RecyclerviewFragmentHomeBinding homeBinding) {
            super(homeBinding.getRoot());
            this.homeBinding = homeBinding;
        }
    }

}
