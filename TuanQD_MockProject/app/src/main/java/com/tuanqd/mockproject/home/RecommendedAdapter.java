package com.tuanqd.mockproject.home;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.baseproject.R;
import com.example.baseproject.databinding.ItemRecommendHomeBinding;

import java.util.List;

public class RecommendedAdapter extends RecyclerView.Adapter<RecommendedAdapter.RecommendedViewHolder> {

    List<RecommendedHomeModel> recommendedHomeModelList;

    public RecommendedAdapter(List<RecommendedHomeModel> recommendedHomeModelList) {
        this.recommendedHomeModelList = recommendedHomeModelList;
    }

    @NonNull
    @Override
    public RecommendedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemRecommendHomeBinding binding = DataBindingUtil.inflate(inflater,
                R.layout.item_recommend_home, parent, false);
        return new RecommendedViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecommendedViewHolder holder, int position) {
        holder.itemRecommendHomeBinding.setRecommendedModel(recommendedHomeModelList.get(position));
        holder.itemRecommendHomeBinding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        if (recommendedHomeModelList != null) {
            return recommendedHomeModelList.size();
        } else {
            return 0;
        }
    }

    public class RecommendedViewHolder extends RecyclerView.ViewHolder {
        private final ItemRecommendHomeBinding itemRecommendHomeBinding;

        public RecommendedViewHolder(@NonNull ItemRecommendHomeBinding itemRecommendHomeBinding) {
            super(itemRecommendHomeBinding.getRoot());
            this.itemRecommendHomeBinding = itemRecommendHomeBinding;
        }
    }
}

