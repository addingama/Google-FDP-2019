package com.google.fdp.moviecatalogue.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.fdp.moviecatalogue.R;
import com.google.fdp.moviecatalogue.activities.MovieDetailActivity;
import com.google.fdp.moviecatalogue.adapters.MovieAdapter;
import com.google.fdp.moviecatalogue.model.Movie;
import com.google.fdp.moviecatalogue.presenter.MoviesPresenter;
import com.google.fdp.moviecatalogue.view.MoviesView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class TvSeriesFragment extends Fragment implements MoviesView, MovieAdapter.OnItemClickCallback{
    private RecyclerView rvTvSeries;
    private MovieAdapter adapter;

    public TvSeriesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tv_series, container, false);

        rvTvSeries = view.findViewById(R.id.rv_tv_series);
        rvTvSeries.setHasFixedSize(true);

        adapter = new MovieAdapter(getActivity());
        adapter.setOnItemClickCallback(this);
        MoviesPresenter presenter = new MoviesPresenter(getActivity(), this);
        presenter.buildMoviesData(true);


        return view;
    }

    @Override
    public void showMovies(ArrayList<Movie> movies) {
        rvTvSeries.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter.setMovies(movies);
        adapter.notifyDataSetChanged();
        rvTvSeries.setAdapter(adapter);
    }

    @Override
    public void onItemClicked(Movie data) {
        if (getActivity() != null) {
            Intent moveWithObjectIntent = new Intent(getActivity(), MovieDetailActivity.class);
            moveWithObjectIntent.putExtra(MovieDetailActivity.EXTRA_MOVIE, data);
            getActivity().startActivity(moveWithObjectIntent);
        }

    }

}
