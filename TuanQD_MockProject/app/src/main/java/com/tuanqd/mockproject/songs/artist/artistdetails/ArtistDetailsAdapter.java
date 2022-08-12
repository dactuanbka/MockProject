package com.tuanqd.mockproject.songs.artist.artistdetails;

import static com.tuanqd.mockproject.songs.artist.artistdetails.ArtistDetailsModel.TOP_ALBUMS;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.baseproject.R;
import com.example.baseproject.databinding.RecyclerViewArtistDetailBinding;

import java.util.List;

public class ArtistDetailsAdapter extends RecyclerView.Adapter<ArtistDetailsAdapter.ArtistDetailViewHolder> {
    List<ArtistDetailsModel> artistDetailsModelList;
    Context mContext;

    public ArtistDetailsAdapter(List<ArtistDetailsModel> artistDetailsModelList, Context mcontext) {
        this.artistDetailsModelList = artistDetailsModelList;
        this.mContext = mcontext;
    }

    @NonNull
    @Override
    public ArtistDetailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        RecyclerViewArtistDetailBinding mArtistDetailsBinding = DataBindingUtil.inflate(inflater,
                R.layout.recycler_view_artist_detail, parent, false);
        return new ArtistDetailViewHolder(mArtistDetailsBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ArtistDetailViewHolder holder, int position) {
        ArtistDetailsModel artistDetailsModel = artistDetailsModelList.get(position);
        holder.binding.recyclerViewArtistDetailsFragment.setAdapter((RecyclerView.Adapter) artistDetailsModel.getAdapter());
        if (holder.getItemViewType() == TOP_ALBUMS) {
            holder.binding.recyclerViewArtistDetailsFragment.setLayoutManager(new LinearLayoutManager(mContext,
                    RecyclerView.HORIZONTAL, false));
        } else {
            holder.binding.recyclerViewArtistDetailsFragment.setLayoutManager(new LinearLayoutManager(mContext,
                    RecyclerView.VERTICAL, false));
        }
        holder.binding.setArtistDetailsModel(artistDetailsModel);
        holder.binding.executePendingBindings();
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);

    }

    @Override
    public int getItemCount() {
        if (artistDetailsModelList != null) {
            return artistDetailsModelList.size();
        } else {
            return 0;
        }
    }

    class ArtistDetailViewHolder extends RecyclerView.ViewHolder {
        private RecyclerViewArtistDetailBinding binding;

        public ArtistDetailViewHolder(@NonNull RecyclerViewArtistDetailBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

}
