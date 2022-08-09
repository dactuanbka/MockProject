package com.tuanqd.mockproject.songs.viewpager_songs;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.loader.app.LoaderManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.baseproject.databinding.FragmentAllSongsBinding;

public class AllSongsFragment extends Fragment {
    private static final int LOADER_DEVICE_ID = 1;
    RecyclerView mRcvAllSongs;
    Cursor mCursor;
    boolean resume=false;

    AllSongsViewModel allSongsViewModel;

    public AllSongsFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static AllSongsFragment newInstance(String param1, String param2) {
        AllSongsFragment fragment = new AllSongsFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        FragmentAllSongsBinding fragmentAllSongsBinding = FragmentAllSongsBinding.inflate(inflater);
        allSongsViewModel = new ViewModelProvider(requireActivity()).get(AllSongsViewModel.class);
        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.READ_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_GRANTED) {
            LoaderManager.getInstance(this).initLoader(LOADER_DEVICE_ID, null, allSongsViewModel);
        }
        mRcvAllSongs = fragmentAllSongsBinding.recyclerViewAllSongs;
        mRcvAllSongs.setLayoutManager((new LinearLayoutManager(requireContext())));
        return fragmentAllSongsBinding.getRoot();
    }

    @Override
    public void onResume() {
        mRcvAllSongs.setAdapter(new AllSongsAdapter(allSongsViewModel.getCursorData()));
        super.onResume();

    }
}