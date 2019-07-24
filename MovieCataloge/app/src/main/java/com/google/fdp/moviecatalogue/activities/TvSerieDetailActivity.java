package com.google.fdp.moviecatalogue.activities;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.fdp.moviecatalogue.R;
import com.google.fdp.moviecatalogue.model.TvSerie;

public class TvSerieDetailActivity extends AppCompatActivity {

    public static final String EXTRA_MOVIE = "extra_tv_serie";

    private TvSerie movie;
    private TextView txtTitle;
    private TextView txtDate;
    private TextView txtDescription;
    private ImageView imgPoster;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tv_serie_detail);


        imgPoster = findViewById(R.id.img_poster);
        txtTitle = findViewById(R.id.txt_title);
        txtDate = findViewById(R.id.txt_date);
        txtDescription = findViewById(R.id.txt_description);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(getResources().getString(R.string.movie_detail));
        }


        movie = getIntent().getParcelableExtra(EXTRA_MOVIE);
        displayMovie();
    }

    private void displayMovie() {
        txtTitle.setText(movie.getName());
        txtDate.setText(movie.getFirstAirDate());
        txtDescription.setText(movie.getOverview());

        Glide.with(this).load(movie.getPosterUrl()).into(imgPoster);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
