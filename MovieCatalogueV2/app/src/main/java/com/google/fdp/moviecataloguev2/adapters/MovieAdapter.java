package com.google.fdp.moviecataloguev2.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.fdp.moviecataloguev2.R;
import com.google.fdp.moviecataloguev2.models.Movie;

import java.util.List;

/**
 * Created by gama on 2019-07-26.
 * Addin Gama Bertaqwa
 * addingama@gmail.com
 */
public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {
    private Context context;
    private List<Movie> movies;
    private OnItemClickCallback onItemClickCallback;

    public MovieAdapter(Context context, List<Movie> movies) {
        this.movies = movies;
        this.context = context;
    }

    public void setOnItemClickCallback(OnItemClickCallback onItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder viewHolder, int position) {
        final Movie movie = movies.get(position);
        Glide.with(viewHolder.itemView.getContext())
                .load(movie.getPosterUrl())
                .into(viewHolder.ivPoster);
        viewHolder.tvTitle.setText(movie.getTitle());
        viewHolder.tvReleased.setText(movie.getReleaseDate());
        viewHolder.tvRating.setText(movie.getRating() + "");
        viewHolder.rbRating.setRating(movie.getRating());

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickCallback.onItemClicked(movie);
            }
        });

    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    class MovieViewHolder extends RecyclerView.ViewHolder {
        ImageView ivPoster;
        TextView tvTitle, tvReleased, tvRating;
        RatingBar rbRating;

        MovieViewHolder(View itemView) {
            super(itemView);
            ivPoster = itemView.findViewById(R.id.ivPoster);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvReleased = itemView.findViewById(R.id.tvReleased);
            tvRating = itemView.findViewById(R.id.tvRating);
            rbRating = itemView.findViewById(R.id.rbRating);
        }
    }

    public interface OnItemClickCallback {
        void onItemClicked(Movie data);
    }

}
