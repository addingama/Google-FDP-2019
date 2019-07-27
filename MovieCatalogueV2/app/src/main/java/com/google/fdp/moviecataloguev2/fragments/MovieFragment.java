package com.google.fdp.moviecataloguev2.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.fdp.moviecataloguev2.MovieDetailActivity;
import com.google.fdp.moviecataloguev2.R;
import com.google.fdp.moviecataloguev2.adapters.MovieAdapter;
import com.google.fdp.moviecataloguev2.models.Movie;
import com.google.fdp.moviecataloguev2.viewmodels.MovieViewModel;

import java.util.List;

public class MovieFragment extends Fragment implements MovieAdapter.OnItemClickCallback {

    public static String TYPE_KEY = "TYPE";
    public static String IS_FAVORITE_KEY = "IS_FAVORITE_MENU";
    public static String MOVIE_KEY = "MOVIE";
    public static String TV_SHOW_KEY = "TV_SHOW";

    private MovieAdapter adapter;
    private MovieViewModel viewModel;


    private RecyclerView rvMovie;
    private ProgressBar pbLoading;


    public MovieFragment() {
        // Required empty public constructor
    }

    public static MovieFragment newInstance(String type, boolean isFavourite) {
        MovieFragment fragment = new MovieFragment();
        Bundle args = new Bundle();
        args.putString(TYPE_KEY, type);
        args.putBoolean(IS_FAVORITE_KEY, isFavourite);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getActivity() != null) {
            viewModel = ViewModelProviders.of(this).get(MovieViewModel.class);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_movie, container, false);
        rvMovie = view.findViewById(R.id.rvMovie);
        pbLoading = view.findViewById(R.id.pbLoading);


        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        adapter = new MovieAdapter(getActivity());
        rvMovie.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvMovie.setAdapter(adapter);
        adapter.setOnItemClickCallback(this);

        Bundle bundle = getArguments();
        if (bundle != null) {
            if (bundle.getBoolean(IS_FAVORITE_KEY)) {
                //
            } else {
                loadMovies();
            }
        }

    }

    private void loadMovies() {
        viewModel.getLiveData().observe(this, onComplete);
        updateItem();
    }

    private void updateItem() {
        showLoading(true);
        Bundle bundle = getArguments();
        if (bundle != null) {
            String type = bundle.getString(TYPE_KEY);
            viewModel.updateItem(type != null ? type : MOVIE_KEY);
        }
    }

    private Observer<List<Movie>> onComplete = new Observer<List<Movie>>() {
        @Override
        public void onChanged(List<Movie> movies) {
            showLoading(false);
            if (movies != null) {
                adapter.setMovies(movies);
                adapter.notifyDataSetChanged();
            }
        }
    };

    private void showLoading(boolean state) {
        if (state) {
            pbLoading.setVisibility(View.VISIBLE);
        } else {
            pbLoading.setVisibility(View.GONE);
        }
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onItemClicked(Movie data) {
        Bundle bundle = getArguments();
        if (bundle != null) {
            String type = bundle.getString(TYPE_KEY);
            MovieDetailActivity.startActivity(getActivity(), data,
                    type != null ? type : MOVIE_KEY);
        }

    }
}
