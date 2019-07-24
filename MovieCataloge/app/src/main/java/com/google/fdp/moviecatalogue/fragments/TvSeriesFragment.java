package com.google.fdp.moviecatalogue.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
    private ProgressBar progressBar;

    private ArrayList<Movie> tvSeries;

    private String TV_SERIES_DATA = "TV_SERIES_DATA";

    public TvSeriesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tv_series, container, false);

        progressBar = view.findViewById(R.id.loading);
        rvTvSeries = view.findViewById(R.id.rv_tv_series);
        rvTvSeries.setHasFixedSize(true);

        adapter = new MovieAdapter(getActivity());
        adapter.setOnItemClickCallback(this);
        MoviesPresenter presenter = new MoviesPresenter(this);
        if (savedInstanceState == null ) {
            presenter.fetchTvSeries();
        }
        return view;
    }

    @Override
    public void showMovies(ArrayList<Movie> movies) {
        rvTvSeries.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter.setMovies(movies);
        tvSeries = movies;
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

    @Override
    public void showLoading(boolean isLoading) {
        if (isLoading) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(TV_SERIES_DATA, this.tvSeries);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null) {
            tvSeries = savedInstanceState.getParcelableArrayList(TV_SERIES_DATA);
            showMovies(tvSeries);
            showLoading(false);
        }
    }

    @Override
    public void showError(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

}
