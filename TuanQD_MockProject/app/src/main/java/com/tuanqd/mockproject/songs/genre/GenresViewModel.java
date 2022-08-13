package com.tuanqd.mockproject.songs.genre;

import androidx.lifecycle.ViewModel;

import com.example.baseproject.R;

import java.util.ArrayList;
import java.util.List;

public class GenresViewModel extends ViewModel {
    List<GenresModel> genresModelList = new ArrayList<>();

    public List<GenresModel> getGenresModelList() {
        return genresModelList;
    }

    public void setGenresModelList(List<GenresModel> genresModelList) {
        this.genresModelList = genresModelList;
    }
    public void initViewModel() {
        if(genresModelList!=null){
            genresModelList.clear();
        }
        genresModelList.add(new GenresModel(R.drawable.ic_classical_genre));
        genresModelList.add(new GenresModel(R.drawable.ic_genres_pop));
        genresModelList.add(new GenresModel(R.drawable.ic_genres_hiphop));
        genresModelList.add(new GenresModel(R.drawable.ic_genres_rock));
        genresModelList.add(new GenresModel(R.drawable.ic_genres_sound_rb));
        genresModelList.add(new GenresModel(R.drawable.ic_genres_instrument));
        genresModelList.add(new GenresModel(R.drawable.ic_genres_jazz));
        genresModelList.add(new GenresModel(R.drawable.ic_genres_country_music));
        setGenresModelList(genresModelList);
    }
}
