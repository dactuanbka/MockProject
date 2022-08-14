package com.tuanqd.mockproject.songs.allsongs;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.baseproject.databinding.FragmentAllSongsBinding;
import com.tuanqd.mockproject.home.repository.AllSongsListRepository;
import com.tuanqd.mockproject.main.BaseViewModel;
import com.tuanqd.mockproject.main.SongsModel;
import com.tuanqd.mockproject.mediaplayer.MusicService;


import java.util.ArrayList;
import java.util.List;

public class AllSongsFragment extends Fragment {
    private static final int LOADER_DEVICE_ID = 1;
    RecyclerView mRcvAllSongs;
    Intent intentAllSongs;
    AllSongsViewModel allSongsViewModel;
    BaseViewModel baseViewModel;
    FragmentAllSongsBinding fragmentAllSongsBinding;
    List<SongsModel> songsModelList = new ArrayList<>();
    AllSongsListRepository allSongsListRepository = AllSongsListRepository.getInstance();

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
        fragmentAllSongsBinding = FragmentAllSongsBinding.inflate(inflater);
        allSongsViewModel = new ViewModelProvider(requireActivity()).get(AllSongsViewModel.class);
        mRcvAllSongs = fragmentAllSongsBinding.recyclerViewAllSongs;
        mRcvAllSongs.setLayoutManager((new LinearLayoutManager(requireContext())));
        if (allSongsListRepository.getAllSongsList() != null) {
            mRcvAllSongs.setAdapter(new AllSongsAdapter(allSongsViewModel,
                    allSongsViewModel.getListData()));
        } else {
            Toast.makeText(requireContext(), "There is no music data ", Toast.LENGTH_SHORT).show();
        }

        return fragmentAllSongsBinding.getRoot();
    }

    @Override
    public void onResume() {
        mRcvAllSongs.setAdapter(new AllSongsAdapter(allSongsViewModel,
                allSongsViewModel.getListData()));
        super.onResume();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }

}