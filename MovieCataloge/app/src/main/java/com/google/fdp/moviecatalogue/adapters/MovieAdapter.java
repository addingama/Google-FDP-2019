package com.google.fdp.moviecatalogue.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.fdp.moviecatalogue.R;
import com.google.fdp.moviecatalogue.model.Movie;

import java.util.ArrayList;

/**
 * Created by gama on 2019-07-01.
 * Addin Gama Bertaqwa
 * addingama@gmail.com
 */
public class MovieAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Movie> movies;

    public void setMovies(ArrayList<Movie> movies) {
        this.movies = movies;
    }
    public MovieAdapter(Context context) {
        this.context = context;
        movies = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return movies.size();
    }

    @Override
    public Object getItem(int i) {
        return movies.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_movie, viewGroup, false);
        }
        ViewHolder viewHolder = new ViewHolder(view);
        Movie movie = (Movie) getItem(i);
        viewHolder.bind(movie);
        return view;
    }

    private class ViewHolder {
        private TextView txtName;
        private TextView txtDescription;
        private ImageView imgPhoto;
        ViewHolder(View view) {
            txtName = view.findViewById(R.id.txt_name);
            txtDescription = view.findViewById(R.id.txt_description);
            imgPhoto = view.findViewById(R.id.img_photo);
        }
        void bind(Movie movie) {
            txtName.setText(movie.getTitle());
            txtDescription.setText(movie.getDate());

            Glide.with(context).load(movie.getImage()).into(imgPhoto);
        }
    }
}
