package com.google.fdp.moviecatalogue.presenter;

import android.content.Context;

import com.google.fdp.moviecatalogue.R;
import com.google.fdp.moviecatalogue.model.Movie;
import com.google.fdp.moviecatalogue.view.MoviesView;

import java.util.ArrayList;

/**
 * Created by gama on 2019-07-01.
 * Addin Gama Bertaqwa
 * addingama@gmail.com
 */
public class MoviesPresenter {
    private MoviesView view;
    private Context context;
    private ArrayList<Movie> movies = new ArrayList<Movie>();

    public MoviesPresenter(Context context, MoviesView view) {
        this.context = context;
        this.view = view;
    }

    public void buildMoviesData() {
        String[] titles = context.getResources().getStringArray(R.array.movies_title);
        String[] images = context.getResources().getStringArray(R.array.movies_image);
        String[] releaseDates = context.getResources().getStringArray(R.array.movies_date);
        String[] descriptions = context.getResources().getStringArray(R.array.movies_description);

        for (int i = 0; i < titles.length; i++) {
            Movie movie = new Movie();
            movie.setTitle(titles[i]);
            movie.setDate(releaseDates[i]);
            movie.setImage(images[i]);
            movie.setDescription(descriptions[i]);
            movies.add(movie);
        }


        this.view.showMovies(movies);

    }

    public Movie getMovie(int index) {
        return movies.get(index);
    }
}
