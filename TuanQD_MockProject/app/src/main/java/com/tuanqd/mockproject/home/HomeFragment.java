package com.tuanqd.mockproject.home;

import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.baseproject.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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
        FragmentHomeBinding fragmentHomeBinding = FragmentHomeBinding.inflate(inflater);
        HomeViewModel homeViewModel = new ViewModelProvider(requireActivity()).
                get(HomeViewModel.class);


        LinearLayoutManager vLayoutManager = new LinearLayoutManager(requireContext(),
                RecyclerView.VERTICAL, false);
        fragmentHomeBinding.recyclerViewHome.setLayoutManager(vLayoutManager);
        homeViewModel.setData();
        HomeAdapter homeAdapter = new HomeAdapter(homeViewModel.getHomeModelList(),requireContext());
        fragmentHomeBinding.recyclerViewHome.setAdapter(homeAdapter);
        return fragmentHomeBinding.getRoot();
    }
}