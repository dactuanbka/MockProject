package com.example.baseproject.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.baseproject.R;
import com.example.baseproject.databinding.ItemRecommendBinding;

public class HotRecommenedAdapter extends RecyclerView.Adapter<HotRecommenedAdapter.RecommentViewHodler> {

    @NonNull
    @Override
    public RecommentViewHodler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(parent.getContext());
        ItemRecommendBinding binding= DataBindingUtil.inflate(inflater,
                R.layout.item_recommend,parent, false);
        return new RecommentViewHodler(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecommentViewHodler holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class RecommentViewHodler extends RecyclerView.ViewHolder{
        private final ItemRecommendBinding binding;
        public RecommentViewHodler(@NonNull ItemRecommendBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}

