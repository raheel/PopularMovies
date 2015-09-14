/*
 * This code was generated for the Popular Movies app 
 * using Android Content Provider Generator
 * More information can be found at:
 * https://github.com/BoD/android-contentprovider-generator
 */
package com.udacity.popularmovies.provider.moviedetails;

import com.udacity.popularmovies.provider.base.BaseModel;

import java.util.Date;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Movie Details
 */
public interface MovieDetailsModel extends BaseModel {

    /**
     * Get the {@code movie_id} value.
     */
    int getMovieId();

    /**
     * JSON of Movie Details
     * Cannot be {@code null}.
     */
    @NonNull
    String getJson();

    /**
     * Get the {@code popularity} value.
     */
    double getPopularity();

    /**
     * Get the {@code vote_average} value.
     */
    double getVoteAverage();
}
