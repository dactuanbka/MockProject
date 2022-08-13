package com.tuanqd.mockproject.songs.albums.albumdetail;

import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.baseproject.R;
import com.example.baseproject.databinding.ItemAlbumDetailFragmentBinding;
import com.tuanqd.mockproject.songs.albums.AlbumModel;

import java.util.List;

public class AlbumDetailAdapter extends RecyclerView.Adapter<AlbumDetailAdapter.AlbumDetailViewHolder> {
    List<AlbumDetailModel> albumDetailModelList;

    public AlbumDetailAdapter(List<AlbumDetailModel> albumDetailModelList) {
        this.albumDetailModelList = albumDetailModelList;
    }

    @NonNull
    @Override
    public AlbumDetailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemAlbumDetailFragmentBinding albumDetailBinding = DataBindingUtil.inflate(inflater,
                R.layout.item_album_detail_fragment, parent, false);
        return new AlbumDetailViewHolder(albumDetailBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull AlbumDetailViewHolder holder, int position) {
        AlbumDetailModel albumDetailModel = albumDetailModelList.get(position);
        holder.mBinding.setAlbumDetailModel(albumDetailModel);
        holder.mBinding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        if (albumDetailModelList != null) {
            return albumDetailModelList.size();
        } else {
            return 0;
        }

    }

    public class AlbumDetailViewHolder extends RecyclerView.ViewHolder {
        private ItemAlbumDetailFragmentBinding mBinding;

        public AlbumDetailViewHolder(@NonNull ItemAlbumDetailFragmentBinding mBinding) {
            super(mBinding.getRoot());
            this.mBinding = mBinding;
        }
    }
}
