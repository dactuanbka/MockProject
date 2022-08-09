package com.tuanqd.mockproject.songs.viewpager_songs;

import android.database.Cursor;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.baseproject.databinding.FragmentAllSongsBinding;

public class AllSongsFragment extends Fragment {
     RecyclerView mRcvAllSongs;
    Cursor mCursor;

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
        FragmentAllSongsBinding fragmentAllSongsBinding= FragmentAllSongsBinding.inflate(inflater);
allSongsViewModel= new ViewModelProvider(requireActivity()).get(AllSongsViewModel.class);
mRcvAllSongs= fragmentAllSongsBinding.recyclerViewAllSongs;
mRcvAllSongs.setLayoutManager((new LinearLayoutManager(requireContext())));
mRcvAllSongs.setAdapter(new AllSongsAdapter(allSongsViewModel.getCursorData()));
        return fragmentAllSongsBinding.getRoot();
    }
}