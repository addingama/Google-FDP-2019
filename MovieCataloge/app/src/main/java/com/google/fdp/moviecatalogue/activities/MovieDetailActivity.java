package com.google.fdp.moviecatalogue.activities;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.fdp.moviecatalogue.R;
import com.google.fdp.moviecatalogue.model.Movie;

public class MovieDetailActivity extends AppCompatActivity {
    public static final String EXTRA_MOVIE = "extra_movie";

    private Movie movie;
    private TextView txtTitle;
    private TextView txtDate;
    private TextView txtDescription;
    private ImageView imgPoster;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);


        imgPoster = findViewById(R.id.img_poster);
        txtTitle = findViewById(R.id.txt_title);
        txtDate = findViewById(R.id.txt_date);
        txtDescription = findViewById(R.id.txt_description);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle("Movie Detail");
        }


        movie = getIntent().getParcelableExtra(EXTRA_MOVIE);
        displayMovie();
    }

    private void displayMovie() {
        txtTitle.setText(movie.getTitle());
        txtDate.setText(movie.getDate());
        txtDescription.setText(movie.getDescription());

        Glide.with(this).load(movie.getImage()).into(imgPoster);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
