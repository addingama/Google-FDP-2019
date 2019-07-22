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
import com.google.fdp.moviecatalogue.activities.TvSerieDetailActivity;
import com.google.fdp.moviecatalogue.adapters.TvSerieAdapter;
import com.google.fdp.moviecatalogue.model.TvSerie;
import com.google.fdp.moviecatalogue.presenter.TvSeriesPresenter;
import com.google.fdp.moviecatalogue.view.TvSeriesView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class TvSeriesFragment extends Fragment implements TvSeriesView, TvSerieAdapter.OnItemClickCallback{
    private RecyclerView rvTvSeries;
    private TvSerieAdapter adapter;
    private ProgressBar progressBar;

    private ArrayList<TvSerie> tvSeries;

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

        adapter = new TvSerieAdapter(getActivity());
        adapter.setOnItemClickCallback(this);
        TvSeriesPresenter presenter = new TvSeriesPresenter(this);
        if (savedInstanceState == null ) {
            presenter.fetchTvSeries();
        }
        return view;
    }

    @Override
    public void showMovies(ArrayList<TvSerie> movies) {
        rvTvSeries.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter.setMovies(movies);
        tvSeries = movies;
        adapter.notifyDataSetChanged();
        rvTvSeries.setAdapter(adapter);
    }

    @Override
    public void onItemClicked(TvSerie data) {
        if (getActivity() != null) {
            Intent moveWithObjectIntent = new Intent(getActivity(), TvSerieDetailActivity.class);
            moveWithObjectIntent.putExtra(TvSerieDetailActivity.EXTRA_MOVIE, data);
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
