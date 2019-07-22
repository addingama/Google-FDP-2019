package com.google.fdp.moviecatalogue.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

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
public class MoviesFragment extends Fragment implements MoviesView, MovieAdapter.OnItemClickCallback {
    private RecyclerView rvMovies;
    private MovieAdapter adapter;
    private ProgressBar progressBar;
    private ArrayList<Movie> movies;

    private String MOVIES_DATA = "MOVIES_DATA";


    public MoviesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_movies, container, false);

        progressBar = view.findViewById(R.id.loading);
        rvMovies = view.findViewById(R.id.rv_movies);
        rvMovies.setHasFixedSize(true);

        adapter = new MovieAdapter(getActivity());
        adapter.setOnItemClickCallback(this);
        MoviesPresenter presenter = new MoviesPresenter(this);
        if (savedInstanceState == null ) {
            presenter.fetchMovies();
        }

        return view;
    }

    @Override
    public void showMovies(ArrayList<Movie> movies) {
        rvMovies.setLayoutManager(new LinearLayoutManager(getActivity()));
        this.movies = movies;
        adapter.setMovies(movies);
        adapter.notifyDataSetChanged();
        rvMovies.setAdapter(adapter);
    }

    @Override
    public void showLoading(boolean isLoading) {
        if (isLoading) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void showError(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemClicked(Movie data) {
        if (getActivity() != null) {
            Intent moveWithObjectIntent = new Intent(getActivity(), MovieDetailActivity.class);
            moveWithObjectIntent.putExtra(MovieDetailActivity.EXTRA_MOVIE, data);
            getActivity().startActivity(moveWithObjectIntent);
        }
    }


    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(MOVIES_DATA, this.movies);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null) {
            movies = savedInstanceState.getParcelableArrayList(MOVIES_DATA);
            showMovies(movies);
            showLoading(false);
        }
    }
}
