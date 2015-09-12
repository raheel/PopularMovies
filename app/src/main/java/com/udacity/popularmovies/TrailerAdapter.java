package com.udacity.popularmovies;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.udacity.popularmovies.rest.Movie;
import com.udacity.popularmovies.rest.Trailer;

import java.util.ArrayList;

/**
 * Created by Raheel Khan on 7/12/2015.
 */
public class TrailerAdapter extends ArrayAdapter<Trailer> {
    private ArrayList<Trailer> trailers;

    public TrailerAdapter(Activity context, ArrayList<Trailer> trailers) {
        super(context, 0, trailers);
        this.trailers = trailers;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Trailer trailer = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_trailer, parent, false);
        }
        System.out.println(position + "- : trailer.getName() = " + trailer.getName());
        TextView view = (TextView) convertView.findViewById(R.id.trailer_name);
        view.setText(trailer.getName());
        return convertView;
    }
}
