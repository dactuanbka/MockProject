package com.tuanqd.mockproject.songs.genre;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.baseproject.R;
import com.example.baseproject.databinding.ItemGenresBinding;

import java.util.List;

public class GenresAdapter extends RecyclerView.Adapter<GenresAdapter.GenresViewHolder> {
    List<GenresModel> genresModelList;

    public GenresAdapter(List<GenresModel> genresModelList) {
        this.genresModelList = genresModelList;
    }

    @NonNull
    @Override
    public GenresViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemGenresBinding itemGenresBinding = DataBindingUtil.inflate(inflater, R.layout.item_genres,
                parent, false);
        return new GenresViewHolder(itemGenresBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull GenresViewHolder holder, int position) {
        holder.genresBinding.setGenresModel(genresModelList.get(position));
        holder.genresBinding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        if (genresModelList != null) {
            return genresModelList.size();
        } else {
            return 0;
        }
    }

    public class GenresViewHolder extends RecyclerView.ViewHolder {
        private ItemGenresBinding genresBinding;

        public GenresViewHolder(@NonNull ItemGenresBinding genresBinding) {
            super(genresBinding.getRoot());
            this.genresBinding = genresBinding;
        }
    }
}
