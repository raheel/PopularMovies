package com.udacity.popularmovies;

import android.app.Activity;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Raheel Khan on 7/12/2015.
 */
public class MovieAdapter extends ArrayAdapter<Movie> {
    private ArrayList<Movie> movies;
    public static String IMAGE_BASE_URL = "http://image.tmdb.org/t/p/";

    public MovieAdapter(Activity context, ArrayList<Movie> movies) {
        super(context, 0, movies);
        this.movies = movies;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Movie movie = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.grid_item_movie, parent, false);
        }

        ImageView posterView = (ImageView) convertView.findViewById(R.id.grid_item_poster);
        Picasso.with(getContext()).load(Movie.getPosterUri(movie)).into(posterView);

        return convertView;
    }

    public ArrayList<Movie> getMovies() {
        return new ArrayList<Movie>(movies);
    }

    public void setMovies(ArrayList<Movie> movies) {
        this.movies = movies;
    }
}
