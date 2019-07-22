package com.google.fdp.moviecatalogue.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.fdp.moviecatalogue.R;
import com.google.fdp.moviecatalogue.model.TvSerie;

import java.util.ArrayList;

/**
 * Created by gama on 2019-07-01.
 * Addin Gama Bertaqwa
 * addingama@gmail.com
 */
public class TvSerieAdapter extends RecyclerView.Adapter<TvSerieAdapter.MovieViewHolder> {
    private ArrayList<TvSerie> movies;

    public void setMovies(ArrayList<TvSerie> movies) {
        this.movies = movies;
    }
    public TvSerieAdapter(Context context) {
        movies = new ArrayList<>();
    }

    private OnItemClickCallback onItemClickCallback;

    public void setOnItemClickCallback(OnItemClickCallback onItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_movie, viewGroup, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder viewHolder, int i) {
        final TvSerie movie = movies.get(i);
        Glide.with(viewHolder.itemView.getContext())
                .load(movie.getPosterPath())
                .into(viewHolder.imgPhoto);
        viewHolder.txtTitle.setText(movie.getName());
        viewHolder.txtDescription.setText(movie.getOverview());

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
        ImageView imgPhoto;
        TextView txtTitle, txtDescription;

        MovieViewHolder(View itemView) {
            super(itemView);
            imgPhoto = itemView.findViewById(R.id.img_photo);
            txtTitle = itemView.findViewById(R.id.txt_title);
            txtDescription = itemView.findViewById(R.id.txt_description);
        }
    }

    public interface OnItemClickCallback {
        void onItemClicked(TvSerie data);
    }
}