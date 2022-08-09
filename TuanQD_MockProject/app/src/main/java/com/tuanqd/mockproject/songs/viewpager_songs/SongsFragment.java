package com.tuanqd.mockproject.songs.viewpager_songs;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.baseproject.databinding.FragmentSongsBinding;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class SongsFragment extends Fragment {
SongsPagerAdapter songsPagerAdapter;
ViewPager2 viewPager;

    public SongsFragment() {
        // Required empty public constructor
    }

    public static SongsFragment newInstance(String param1, String param2) {
        SongsFragment fragment = new SongsFragment();
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
        FragmentSongsBinding fragmentSongsBinding= FragmentSongsBinding.inflate(inflater);
        // Inflate the layout for this fragment
        songsPagerAdapter = new SongsPagerAdapter(this);
        viewPager= fragmentSongsBinding.pagerSongsFragment;
        viewPager.setPageTransformer(new ZoomOutPageTransformer());
        viewPager.setAdapter(songsPagerAdapter);
        TabLayout tabLayout= fragmentSongsBinding.tabLayoutSongsFragment;

        new TabLayoutMediator(tabLayout,viewPager,(tab, position) -> {
            switch(position){
                case 0 :
                    tab.setText("All Songs");
                    break;
                case 1:
                    tab.setText("Playlist");
                    break;
                case 2:
                    tab.setText("Albums");
                    break;
                case 3:
                    tab.setText("Artist");
                    break;
                case 4:
                    tab.setText("Genre");
                    break;
            }
        }).attach();
        return fragmentSongsBinding.getRoot();
    }

}
