package com.tuanqd.mockproject.songs.artist;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.loader.app.LoaderManager;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.baseproject.R;
import com.example.baseproject.databinding.FragmentArtistBinding;

public class ArtistFragment extends Fragment implements ArtistAdapter.ClickItemMenu {
    private static final int LOADER_DEVICE_ID2 = 2;
    Cursor artistCursor;

    public ArtistFragment() {
        // Required empty public constructor
    }

    public static ArtistFragment newInstance(String param1, String param2) {
        ArtistFragment fragment = new ArtistFragment();
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
        FragmentArtistBinding fragmentArtistBinding = FragmentArtistBinding.inflate(inflater);
        ArtistViewModel artistViewModel = new ViewModelProvider(requireActivity()).get(ArtistViewModel.class);
        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.READ_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_GRANTED) {
            LoaderManager.getInstance(this).initLoader(LOADER_DEVICE_ID2, null, artistViewModel);
            artistViewModel.getFinishLoaderArtist().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
                @Override
                public void onChanged(Boolean aBoolean) {
                    if (aBoolean) {
                        artistCursor = artistViewModel.getArtistCursor();
                        fragmentArtistBinding.recyclerViewArtist.setLayoutManager(new LinearLayoutManager(requireContext()));
                        fragmentArtistBinding.recyclerViewArtist.setAdapter(new ArtistAdapter(artistCursor,
                                requireContext(), ArtistFragment.this));
                    }
                }
            });
        }
        return fragmentArtistBinding.getRoot();
    }

    @Override
    public void itemPopupClick() {
        Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_content_main)
                .navigate(R.id.artistDetailsFragment);
    }
}