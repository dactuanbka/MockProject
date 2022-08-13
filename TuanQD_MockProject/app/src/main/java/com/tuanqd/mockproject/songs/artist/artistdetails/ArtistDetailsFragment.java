package com.tuanqd.mockproject.songs.artist.artistdetails;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.baseproject.R;
import com.example.baseproject.databinding.FragmentArtistDetailsBinding;
import com.tuanqd.mockproject.songs.artist.ArtistAdapter;

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
        binding.recyclerViewArtistDetailsFragment.setLayoutManager(new LinearLayoutManager(requireContext(),
                RecyclerView.VERTICAL, false));
        ArtistDetailViewModel artistDetailViewModel = new ViewModelProvider(requireActivity()).
                get(ArtistDetailViewModel.class);
        artistDetailViewModel.setArtistDetailAdapter();
        ArtistDetailsAdapter adapter = new ArtistDetailsAdapter(artistDetailViewModel.
                getArtistDetailsModelList(), requireContext());
        binding.recyclerViewArtistDetailsFragment.setAdapter(adapter);
        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i("Detail Arist onResume","");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i("Detail Arist onPause","");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.i("Detail Arist onStop","");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("Detail Arist onDestroy","");
    }
}