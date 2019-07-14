package com.google.fdp.moviecatalogue.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.fdp.moviecatalogue.R;
import com.google.fdp.moviecatalogue.adapters.MovieAdapter;
import com.google.fdp.moviecatalogue.model.Movie;
import com.google.fdp.moviecatalogue.presenter.MainPresenter;
import com.google.fdp.moviecatalogue.view.MainView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MainView, AdapterView.OnItemClickListener {

    private MovieAdapter adapter;
    private MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adapter = new MovieAdapter(this);

        presenter = new MainPresenter(this, this);
        presenter.buildMoviesData();


        ListView listView = findViewById(R.id.lv_list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
    }

    @Override
    public void showMovies(ArrayList<Movie> movies) {
        adapter.setMovies(movies);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Movie movie = presenter.getMovie(i);

        Intent moveWithObjectIntent = new Intent(MainActivity.this, MovieDetailActivity.class);
        moveWithObjectIntent.putExtra(MovieDetailActivity.EXTRA_MOVIE, movie);
        startActivity(moveWithObjectIntent);
    }
}
