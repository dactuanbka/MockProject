package com.tuanqd.mockproject.songs.artist.artistdetails;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.baseproject.databinding.FragmentArtistDetailsBinding;

public class ArtistDetailsFragment extends Fragment {


    public ArtistDetailsFragment() {
        // Required empty public constructor
    }

    public static ArtistDetailsFragment newInstance(String param1, String param2) {
        ArtistDetailsFragment fragment = new ArtistDetailsFragment();
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
        FragmentArtistDetailsBinding binding = FragmentArtistDetailsBinding.inflate(inflater);
        ArtistDetailViewModel artistDetailViewModel = new ViewModelProvider(requireActivity()).
                get(ArtistDetailViewModel.class);
        artistDetailViewModel.setArtistDetailAdapter();
        ArtistDetailsAdapter adapter = new ArtistDetailsAdapter(artistDetailViewModel.getArtistDetailsModelList(),
                requireContext());
        binding.recyclerViewArtistDetailsFragment.setAdapter(adapter);
        return binding.getRoot();
    }
}