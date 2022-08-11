package com.tuanqd.mockproject.songs.genre;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.baseproject.R;
import com.example.baseproject.databinding.FragmentGenreBinding;
import com.tuanqd.mockproject.songs.allsongs.AllSongsViewModel;

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
        FragmentGenreBinding fragmentGenreBinding = FragmentGenreBinding.inflate(inflater);
        GenresViewModel genresViewModel = new ViewModelProvider(requireActivity()).get(GenresViewModel.class);
        genresViewModel.initViewModel();
        fragmentGenreBinding.recyclerViewGenres.setLayoutManager(new GridLayoutManager(requireContext(), 2));
        GenresAdapter genresAdapter = new GenresAdapter(genresViewModel.getGenresModelList());
        fragmentGenreBinding.recyclerViewGenres.setAdapter(genresAdapter);
        return fragmentGenreBinding.getRoot();
    }
}