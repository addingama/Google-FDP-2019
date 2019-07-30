package com.google.fdp.moviecataloguev2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.bumptech.glide.Glide;
import com.google.fdp.moviecataloguev2.models.Movie;
import com.google.fdp.moviecataloguev2.viewmodels.MovieViewModel;

public class MovieDetailActivity extends AppCompatActivity {

    private static String MOVIE_DATA_KEY = "MOVIE_DATA";
    private static String TYPE_KEY = "TYPE";

    private MovieViewModel viewModel;
    private Movie movie;
    private boolean isFavourite = false;

    private ImageView ivPoster;
    private TextView tvTitle;
    private TextView tvReleased;
    private TextView tvLanguage;
    private TextView tvRating;
    private RatingBar rbRating;
    private TextView tvOverviewValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        ivPoster = findViewById(R.id.ivPoster);
        tvTitle = findViewById(R.id.tvTitle);
        tvReleased = findViewById(R.id.tvReleased);
        tvLanguage = findViewById(R.id.tvLanguage);
        tvRating = findViewById(R.id.tvRating);
        tvOverviewValue = findViewById(R.id.tvOverviewValue);
        rbRating = findViewById(R.id.rbRating);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
        }

        viewModel = ViewModelProviders.of(this).get(MovieViewModel.class);


        movie = getIntent().getParcelableExtra(MOVIE_DATA_KEY);
        displayMovie();
    }

    public static void startActivity(Context context, Movie movie, String type) {
        if (context != null) {
            Intent intent = new Intent(context, MovieDetailActivity.class);
            Bundle bundle = new Bundle();

            bundle.putParcelable(MOVIE_DATA_KEY, movie);
            bundle.putString(TYPE_KEY, type);
            intent.putExtras(bundle);
            context.startActivity(intent);
        }
    }

    private void displayMovie() {

        tvTitle.setText(movie.getTitle());
        tvReleased.setText(movie.getReleaseDate());
        tvLanguage.setText(movie.getOriginalLanguage());
        tvRating.setText(movie.getRating() + "");
        rbRating.setRating(movie.getRating());
        tvOverviewValue.setText(movie.getOverview());

        Glide.with(this).load(movie.getPosterUrl()).into(ivPoster);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.detail_menu, menu);
        viewModel.isFavourite(movie.getId() != null ? movie.getId() : 0, getIntent().getStringExtra(TYPE_KEY)).observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean status) {
                isFavourite = status;
                if(isFavourite) {
                    menu.findItem(R.id.navigation_favorite).setIcon(ContextCompat.getDrawable(MovieDetailActivity.this, R.drawable.ic_favorite_black_24dp));
                } else {
                    menu.findItem(R.id.navigation_favorite).setIcon(ContextCompat.getDrawable(MovieDetailActivity.this, R.drawable.ic_favorite_border_black_24dp));
                }
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            case R.id.navigation_favorite:
                if (isFavourite) {
                    viewModel.deleteFavourite(movie, getIntent().getStringExtra(TYPE_KEY));
                } else {
                    viewModel.insertFavourite(movie, getIntent().getStringExtra(TYPE_KEY));
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
