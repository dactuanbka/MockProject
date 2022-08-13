package com.tuanqd.mockproject.songs.albums;

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
import androidx.recyclerview.widget.GridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.baseproject.R;
import com.example.baseproject.databinding.FragmentAlbumsBinding;

public class AlbumsFragment extends Fragment implements AlbumAdapter.ClickItemMenuAlbum {
    private static final int LOADER_DEVICE_ID3 = 3;
    Cursor albumCursor;
    AlbumViewModel albumViewModel;

    public AlbumsFragment() {
        // Required empty public constructor
    }

    public static AlbumsFragment newInstance(String param1, String param2) {
        AlbumsFragment fragment = new AlbumsFragment();
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
        FragmentAlbumsBinding fragmentAlbumsBinding = FragmentAlbumsBinding.inflate(inflater);
        albumViewModel = new ViewModelProvider(requireActivity()).get(AlbumViewModel.class);
        fragmentAlbumsBinding.recyclerViewAlbum.setLayoutManager(new GridLayoutManager(requireContext(),
                2));
        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.READ_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_GRANTED) {
            LoaderManager.getInstance(this).initLoader(LOADER_DEVICE_ID3, null, albumViewModel);
            albumViewModel.getFinishLoaderAlbum().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
                @Override
                public void onChanged(Boolean aBoolean) {
                    if (aBoolean) {
                        albumCursor = albumViewModel.getAlbumCursor();
                        fragmentAlbumsBinding.recyclerViewAlbum.setAdapter(new AlbumAdapter(albumCursor,
                                requireContext(), AlbumsFragment.this));
                    }
                }
            });
        }
        return fragmentAlbumsBinding.getRoot();
    }

    @Override
    public void itemPopupAlbumClick(String albumId) {
        albumViewModel.setPositionAlbumDetail(albumId);
        Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_content_main)
                .navigate(R.id.albumDetailFragment);
    }
}