package com.tuanqd.mockproject.songs.allsongs;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.loader.app.LoaderManager;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.baseproject.R;
import com.example.baseproject.databinding.FragmentAllSongsBinding;
import com.tuanqd.mockproject.mediaplayer.MusicService;

public class AllSongsFragment extends Fragment implements AllSongsAdapter.SongInAllSongsClicked {
    private static final int LOADER_DEVICE_ID = 1;
    RecyclerView mRcvAllSongs;
    Intent intentAllSongs;
    AllSongsViewModel allSongsViewModel;
    Drawable mDivider;
    FragmentAllSongsBinding fragmentAllSongsBinding;

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
        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.READ_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_GRANTED) {
            LoaderManager.getInstance(this).initLoader(LOADER_DEVICE_ID, null, allSongsViewModel);
        }
        mRcvAllSongs = fragmentAllSongsBinding.recyclerViewAllSongs;
        mRcvAllSongs.setLayoutManager((new LinearLayoutManager(requireContext())));
        // make divider_color:
        setDividerColor();
        registerBroadcast();

        return fragmentAllSongsBinding.getRoot();
    }

    private void setDividerColor() {
        mDivider = ContextCompat.getDrawable(requireContext(), R.drawable.divider);
        DividerItemDecoration vItemDecoration = new DividerItemDecoration(requireContext(),1);
        vItemDecoration.setDrawable(mDivider);
        fragmentAllSongsBinding.recyclerViewAllSongs.addItemDecoration(
                vItemDecoration);
    }

    @Override
    public void onResume() {
        mRcvAllSongs.setAdapter(new AllSongsAdapter(allSongsViewModel.getCursorData(), this));
        super.onResume();
    }

    private void registerBroadcast() {
        IntentFilter filter = new IntentFilter("statusMediaPlayer");
        LocalBroadcastManager.getInstance(requireContext()).registerReceiver(musicBroadcastReceiver, filter);
        IntentFilter filter2 = new IntentFilter("remainTimer");
        LocalBroadcastManager.getInstance(requireContext()).registerReceiver(musicBroadcastReceiver, filter2);
    }

    private final BroadcastReceiver musicBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            // all song register for notification
        }
    };

    @Override
    public void songOnClick(int position) {
        intentAllSongs = new Intent(requireContext(), MusicService.class);
        intentAllSongs.putExtra("AllSongsStart", 1);
        intentAllSongs.putExtra("positionAllSong", position);
        requireContext().startService(intentAllSongs);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (intentAllSongs != null) {
            requireContext().stopService(intentAllSongs);
        }
    }
}