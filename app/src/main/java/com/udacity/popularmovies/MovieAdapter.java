package com.udacity.popularmovies;

import android.app.Activity;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.udacity.popularmovies.rest.Movie;

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
        ViewHolder viewHolder;

        Movie movie = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.grid_item_movie, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.imageView = (ImageView) convertView.findViewById(R.id.grid_item_poster);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Picasso.with(getContext()).load(Movie.getPosterUri(movie)).into(viewHolder.imageView);

        return convertView;
    }

    public ArrayList<Movie> getMovies() {
        return new ArrayList<Movie>(movies);
    }

    private static class ViewHolder {
        ImageView imageView;
    }
}
