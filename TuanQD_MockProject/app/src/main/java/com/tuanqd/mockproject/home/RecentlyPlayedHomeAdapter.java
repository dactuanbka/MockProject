package com.tuanqd.mockproject.home;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.baseproject.R;
import com.example.baseproject.databinding.ItemRecentlyPlayedHomeBinding;

import java.util.List;

public class RecentlyPlayedHomeAdapter extends RecyclerView.Adapter<RecentlyPlayedHomeAdapter.RecentlyPlayedViewHolder> {
    List<RecentlyPlayedHomeModel> recentlyPlayedHomeModelList;

    public RecentlyPlayedHomeAdapter(List<RecentlyPlayedHomeModel> recentlyPlayedHomeModelList) {
        this.recentlyPlayedHomeModelList = recentlyPlayedHomeModelList;
    }

    @NonNull
    @Override
    public RecentlyPlayedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemRecentlyPlayedHomeBinding itemRecentlyPlayedHomeBinding = DataBindingUtil.inflate(inflater,
                R.layout.item_recently_played_home, parent, false);
        return new RecentlyPlayedViewHolder(itemRecentlyPlayedHomeBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecentlyPlayedViewHolder holder, int position) {
        holder.itemRecentlyPlayedHomeBinding.
                setRecentlyPlayedHomeModel(recentlyPlayedHomeModelList.get(position));
        holder.itemRecentlyPlayedHomeBinding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        if (recentlyPlayedHomeModelList!=null) {
            return recentlyPlayedHomeModelList.size();
        } else {
            return 0;
        }
    }

    public class RecentlyPlayedViewHolder extends RecyclerView.ViewHolder {
        ItemRecentlyPlayedHomeBinding itemRecentlyPlayedHomeBinding;

        public RecentlyPlayedViewHolder(@NonNull ItemRecentlyPlayedHomeBinding itemRecentlyPlayedHomeBinding) {
            super(itemRecentlyPlayedHomeBinding.getRoot());
            this.itemRecentlyPlayedHomeBinding= itemRecentlyPlayedHomeBinding;
        }
    }
}
