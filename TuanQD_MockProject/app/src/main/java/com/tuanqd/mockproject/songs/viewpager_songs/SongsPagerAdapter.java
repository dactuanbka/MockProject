package com.tuanqd.mockproject.songs.viewpager_songs;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class SongsPagerAdapter extends FragmentStateAdapter {
    Fragment fragment;
    public SongsPagerAdapter(@NonNull Fragment fragment) {
        super(fragment);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        //Fragments
        switch(position){
            case 0 :
                fragment= new AllSongsFragment();
                break;
            case 1 :
                fragment= new PlaylistSongsFragment();
                break;
            case 2:
                fragment= new AlbumsFragment();
                break;
            case 3:
                fragment = new ArtistFragment();
                break;
            case 4:
                fragment= new GenreFragment();
                break;
        }
        return fragment;
    }

    @Override
    public int getItemCount() {
        return 5;
    }
}
