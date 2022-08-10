package com.tuanqd.mockproject.songs.playlist;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.baseproject.R;

public class PlaylistSongsFragment extends Fragment {


    public PlaylistSongsFragment() {
        // Required empty public constructor
    }
    // TODO: Rename and change types and number of parameters
    public static PlaylistSongsFragment newInstance(String param1, String param2) {
        PlaylistSongsFragment fragment = new PlaylistSongsFragment();
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
        return inflater.inflate(R.layout.fragment_playlist_songs, container, false);
    }
}