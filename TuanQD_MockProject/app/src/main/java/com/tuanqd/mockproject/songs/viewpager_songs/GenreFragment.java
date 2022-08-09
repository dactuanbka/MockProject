package com.tuanqd.mockproject.songs.viewpager_songs;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.baseproject.R;

public class GenreFragment extends Fragment {



    public GenreFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static GenreFragment newInstance(String param1, String param2) {
        GenreFragment fragment = new GenreFragment();
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
        return inflater.inflate(R.layout.fragment_genre, container, false);
    }
}