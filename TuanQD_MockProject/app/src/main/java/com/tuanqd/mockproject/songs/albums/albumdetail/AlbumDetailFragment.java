package com.tuanqd.mockproject.songs.albums.albumdetail;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.baseproject.databinding.FragmentAlbumDetailBinding;
import com.tuanqd.mockproject.songs.albums.AlbumViewModel;

import java.util.ArrayList;
import java.util.List;

public class AlbumDetailFragment extends Fragment {
    List<AlbumDetailModel> albumDetailModelList = new ArrayList<>();

    public AlbumDetailFragment() {
        // Required empty public constructor
    }

    public static AlbumDetailFragment newInstance(String param1, String param2) {
        AlbumDetailFragment fragment = new AlbumDetailFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        FragmentAlbumDetailBinding albumDetailBinding = FragmentAlbumDetailBinding.inflate(inflater);
        AlbumDetailViewModel albumDetailViewModel = new ViewModelProvider(requireActivity()).
                get(AlbumDetailViewModel.class);
        // share ViewModel
        AlbumViewModel albumViewModel = new ViewModelProvider(requireActivity()).get(AlbumViewModel.class);
        albumDetailModelList = albumDetailViewModel.initAlbumDetailList(albumViewModel.getPositionAlbumDetail());// truyen argument vao day
        albumDetailBinding.recyclerViewAlbumDetailFragment.
                setLayoutManager(new LinearLayoutManager(requireContext()));
        albumDetailBinding.recyclerViewAlbumDetailFragment.
                setAdapter(new AlbumDetailAdapter(albumDetailModelList));
        return albumDetailBinding.getRoot();
    }
}